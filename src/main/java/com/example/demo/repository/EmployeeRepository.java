package com.example.demo.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Employee;

@Repository
public interface EmployeeRepository extends MongoRepository<Employee, String> {

	Employee findEmployeeById(String id);

	List<Employee> findByIsDeleted(boolean b, Pageable pageable);

	Employee findEmployeeByIdAndIsDeleted(String id, boolean b);

}