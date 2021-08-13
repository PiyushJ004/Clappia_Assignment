package com.example.demo.service;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.json.simple.parser.ParseException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Employee;
import com.example.demo.entity.request.EmployeeCreateRequest;
import com.example.demo.entity.request.EmployeeUpdateRequest;
import com.fasterxml.jackson.core.JsonProcessingException;

@Service
public interface EmployeeService {

	public Employee createNewEmployee(EmployeeCreateRequest employee);

	public List<Employee> getAllEmployees(Pageable pageable);

	public Employee getExistingEmployee(String id);

	public void deleteExistingEmployee(String id);

	public Employee updateEmployee(@Valid EmployeeUpdateRequest request, String id)
			throws JsonProcessingException, ParseException;

//	public List<Employee> searchMultiField(String name, Pageable pageable) throws IOException;

}
