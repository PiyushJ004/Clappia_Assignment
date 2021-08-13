package com.example.demo.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.json.simple.parser.ParseException;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Employee;
import com.example.demo.entity.request.EmployeeCreateRequest;
import com.example.demo.entity.request.EmployeeUpdateRequest;
import com.fasterxml.jackson.core.JsonProcessingException;


public interface EmployeeController {

	@RequestMapping(value = "employee/create", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public ResponseEntity<ModelMap> createEmployee(@Valid @RequestBody EmployeeCreateRequest employee);

	@RequestMapping(value = "employee/fetchAll", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<Employee>> fetchAllEmployee(Pageable pageable);

	@RequestMapping(value = "employee/fetchBy/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Employee> fetchEmployee(@PathVariable String id);

	@RequestMapping(value = "employee/delete/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<Void> deleteEmployee(@PathVariable String id);

	@RequestMapping(value = "employee/update/{id}", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<Employee> updateEmployee(@Valid @RequestBody EmployeeUpdateRequest request,
			@PathVariable String id) throws JsonProcessingException, ParseException;

//	@RequestMapping(value = "searchMultiField/{name}", method = RequestMethod.GET, produces = "application/json")
//	public ResponseEntity<List<Employee>> serachByMultiField(@PathVariable String name, Pageable pageable)
//			throws IOException;
}
