package com.example.demo.service.impl;

import java.util.List;

import javax.validation.Valid;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.example.demo.entity.Employee;
import com.example.demo.entity.request.EmployeeCreateRequest;
import com.example.demo.entity.request.EmployeeUpdateRequest;
import com.example.demo.exception.EmployeeNotFoundException;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.EmployeeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class EmployeeServiceImpl implements EmployeeService {

//	private RestHighLevelClient client;
//
//	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
//
//	public EmployeeServiceImpl(@Qualifier("esClient") RestHighLevelClient esClient) {
//		this.client = esClient;
//		OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//	}

	@Autowired
	private CacheManager cacheManger;

	@Autowired
	private EmployeeRepository employeeRepository;

	public Employee createNewEmployee(EmployeeCreateRequest employee) {
//		Employee empl = new Employee();
//		for(int i = 0; i < 1000000; i++) {
//			empl.setName("Piyush-" + String.valueOf(i));
//			if(i%2 == 0){
//				empl.setGender("M");
//			}else{
//				empl.setGender("F");
//			}
//			empl.setTeam(employee.getTeam() + String.valueOf(i));
//			empl.setSalary(employee.getSalary());
//			if(i%2 == 0){
//				empl.setDeleted(true);
//			}else{
//				empl.setDeleted(false);
//			}
//			employeeRepository.save(empl);
//		}

		Employee empl = new Employee();

		empl.setGender(employee.getGender());
		empl.setName(employee.getName());
		empl.setSalary(employee.getSalary());
		empl.setTeam(employee.getTeam());

		employeeRepository.save(empl);

		Cache cache = cacheManger.getCache("Employee");
		if (cache != null) {
			cache.put(empl.getId() + "_" + empl.isDeleted(), empl);
		}
		return empl;
	}

	public List<Employee> getAllEmployees(@PageableDefault(page = 0, size = 10) Pageable pageable) {
		return employeeRepository.findByIsDeleted(false, pageable);
	}

	public Employee getExistingEmployee(String id) {
		return getEmployeeDetails(id);
	}

	public void deleteExistingEmployee(String id) {
		Employee empl = getEmployeeDetails(id);
		empl.setDeleted(true);
		employeeRepository.save(empl);

		Cache cache = cacheManger.getCache("Employee");
		if (cache != null) {
			cache.evictIfPresent(id + "_" + true);
		}
		return;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Employee updateEmployee(@Valid EmployeeUpdateRequest request, String id)
			throws JsonProcessingException, ParseException {

		Employee empl = getEmployeeDetails(id);
		Cache cache = cacheManger.getCache("Employee");
		if (cache != null) {
			cache.evictIfPresent(id + "_" + true);
		}
		JSONObject payloadObject = (JSONObject) new JSONParser().parse(new ObjectMapper().writeValueAsString(request));
		JSONObject dbObject = (JSONObject) new JSONParser().parse(new ObjectMapper().writeValueAsString(empl));
		for (Object obj : payloadObject.keySet()) {
			String param = (String) obj;
			log.info("Parm : {}", param);
			dbObject.put(param, payloadObject.get(param));
		}
		Employee resp = employeeRepository.save(new ObjectMapper().readValue(dbObject.toJSONString(), Employee.class));

		if (cache != null) {
			cache.put(empl.getId() + "_" + empl.isDeleted(), resp);
		}

		return resp;
	}

	public Employee getEmployeeDetails(String id) {
		Employee empl = null;
		Cache cache = cacheManger.getCache("Employee");
		if (cache != null) {
			empl = cache.get(id + "_" + false, Employee.class);
		}
		if (ObjectUtils.isEmpty(empl)) {
			empl = employeeRepository.findEmployeeByIdAndIsDeleted(id, false);
		}
		if (!ObjectUtils.isEmpty(empl)) {
			return empl;
		}
		throw new EmployeeNotFoundException(String.format("Employee with id %s not found", id));
	}

//	@Override
//	public List<Employee> searchMultiField(String name, Pageable pageable) throws IOException {
//		int skipRecords = pageable.getPageSize() * pageable.getPageNumber();
//		int maxRecords = pageable.getPageSize();
//		log.info("SkipRecords : {}", skipRecords);
//		log.info("MaxRecords : {}", maxRecords);
//		QueryBuilder qb = QueryBuilders.boolQuery().must(QueryBuilders.matchQuery("name", name))
//				.must(matchQuery("isDeleted", false));
//		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder().query(qb).explain(true).from(skipRecords)
//				.size(maxRecords);
//
//		pageable.getSort().iterator().forEachRemaining(sort -> {
//			String property = sort.getProperty();
//			if (!property.endsWith(".keyword")) {
//				property = property.concat(".keyword");
//			}
//			searchSourceBuilder.sort(property,
//					Objects.equals(sort.getDirection(), Direction.DESC) ? SortOrder.DESC : SortOrder.ASC);
//		});
//		SearchHits searchHits = client
//				.search(new SearchRequest("employee").source(searchSourceBuilder), RequestOptions.DEFAULT).getHits();
//
//		long totalRecords = searchHits.getTotalHits().value;
//		log.info("::::::totalHits : {}", totalRecords);
//		return Arrays.stream(searchHits.getHits()).map(documentFields -> {
//			try {
//				Employee customer = OBJECT_MAPPER.readValue(documentFields.getSourceAsString(), Employee.class);
//				customer.setId(documentFields.getId());
//				return customer;
//			} catch (IOException e) {
//				log.error("unable to convert document to class :: ", e);
//			}
//			return null;
//		}).filter(Objects::nonNull).collect(Collectors.toList());
//
//	}
}
