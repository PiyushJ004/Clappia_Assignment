package com.example.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.json.simple.parser.ParseException;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.entity.Employee;
import com.example.demo.entity.request.EmployeeCreateRequest;
import com.example.demo.entity.request.EmployeeUpdateRequest;
import com.fasterxml.jackson.core.JsonProcessingException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaPKMSTServerCodegen", date = "2021-04-16T06:00"
		+ ":39.859Z")
@Api(value = "EmployeeController", description = "The EmployeeRestController API")
public interface EmployeeController {

	@ApiOperation(value = "Api to create a employee.", notes = "", response = Employee.class, tags = {})
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Created", response = Employee.class),
			@ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Not Found") })
	@RequestMapping(value = "employee", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public ResponseEntity<ModelMap> createEmployeeUsingPOST(@Valid @RequestBody EmployeeCreateRequest employee);

	@ApiOperation(value = "Api to retrieve all the available active employee .", notes = "", response = Employee.class, responseContainer = "List", tags = {})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = Employee.class),
			@ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Not Found") })
	@RequestMapping(value = "employee", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<Employee>> fetchAllEmployeeUsingGET(Pageable pageable);

	@ApiOperation(value = "Api to get a employee by employeeId", notes = "", response = Employee.class, tags = {})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = Employee.class),
			@ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Not Found") })
	@RequestMapping(value = "employee/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Employee> fetchEmployeeByIdUsingGET(@PathVariable String id);

	@ApiOperation(value = "Api to delete a employee provided the employeeId.", notes = "", response = Void.class, tags = {})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 204, message = "No Content, template is deleted."),
			@ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Template Not Found for given Id.") })
	@RequestMapping(value = "employee/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<Void> deleteEmployeeByIdUsingDELETE(@PathVariable String id);

	@ApiOperation(value = "Api to update the employee details by employeeId", notes = "", response = Employee.class, tags = {})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = Employee.class),
			@ApiResponse(code = 401, message = "Bad Request"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Not Found") })
	@RequestMapping(value = "employee/{id}", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<Employee> updateEmployeeByIdUsingPUT(@Valid @RequestBody EmployeeUpdateRequest request,
			@PathVariable String id) throws JsonProcessingException, ParseException;

//	@RequestMapping(value = "searchMultiField/{name}", method = RequestMethod.GET, produces = "application/json")
//	public ResponseEntity<List<Employee>> serachByMultiField(@PathVariable String name, Pageable pageable)
//			throws IOException;
}
