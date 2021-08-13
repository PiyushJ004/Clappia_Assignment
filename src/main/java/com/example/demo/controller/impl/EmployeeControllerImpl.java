package com.example.demo.controller.impl;

import java.util.List;

import javax.validation.Valid;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.controller.EmployeeController;
import com.example.demo.entity.Employee;
import com.example.demo.entity.request.EmployeeCreateRequest;
import com.example.demo.entity.request.EmployeeUpdateRequest;
import com.example.demo.service.EmployeeService;
import com.fasterxml.jackson.core.JsonProcessingException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/v1.0/")
public class EmployeeControllerImpl implements EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@Override
	public ResponseEntity<ModelMap> createEmployeeUsingPOST(@RequestBody EmployeeCreateRequest employee) {
		log.info("::::::--->Inside createEmployeeUsingPOST Controller<---::::::");
		Employee empl = employeeService.createNewEmployee(employee);
		log.info("::::::--->Employee details saved successfully<---::::::");
		return ResponseEntity.status(HttpStatus.CREATED).body(new ModelMap().addAttribute("id", empl.getId()));

	}

	@Override
	public ResponseEntity<List<Employee>> fetchAllEmployeeUsingGET(Pageable pageable) {
		log.info("::::::--->Inside fetchAllEmployeeUsingGET Controller<---::::::");
		return ResponseEntity.status(HttpStatus.OK).body(employeeService.getAllEmployees(pageable));

	}

	@Override
	public ResponseEntity<Employee> fetchEmployeeByIdUsingGET(@PathVariable String id) {
		log.info("::::::--->Inside fetchEmployeeByIdUsingGET Controller<---::::::");
		return ResponseEntity.status(HttpStatus.OK).body(employeeService.getExistingEmployee(id));

	}

	@Override
	public ResponseEntity<Void> deleteEmployeeByIdUsingDELETE(@PathVariable String id) {
		log.info("::::::--->Inside deleteEmployeeByIdUsingDELETE Controller<---::::::");
		employeeService.deleteExistingEmployee(id);
		log.info("::::::--->Employee details deleted successfully<---::::::");
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	@Override
	public ResponseEntity<Employee> updateEmployeeByIdUsingPUT(@Valid EmployeeUpdateRequest request, String id)
			throws JsonProcessingException, ParseException {
		log.info("::::::--->Inside updateEmployeeByIdUsingPUT Controller<---::::::");
		return ResponseEntity.status(HttpStatus.OK).body(employeeService.updateEmployee(request, id));
	}

//	public ResponseEntity<List<Employee>> serachByMultiField(@PathVariable String name, Pageable pageable)
//			throws IOException {
//		log.info(":::::::::::Inside search 1:::::::");
//		log.info("name:" + name);	
//		return ResponseEntity.status(HttpStatus.OK).body(employeeService.searchMultiField(name, pageable));
//	}

}