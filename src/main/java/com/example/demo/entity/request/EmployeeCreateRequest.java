package com.example.demo.entity.request;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployeeCreateRequest implements Serializable {
	/**
		 * 
		 */
	private static final long serialVersionUID = 8364647271014710851L;

	private String name;
	private Double salary;
	private String gender;
	private String team;
}
