package com.example.demo.entity;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@CompoundIndexes({ @CompoundIndex(def = "{'id':1, 'isDeleted':1}", name = "id_1_isDeleted_1") })
@Getter
@Setter
@Document(collection = "Employee")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Employee extends EmployeeAddress implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9056399924542673159L;
	@Id
	private String id;

	@NotBlank
	@JsonProperty("name")
	private String name;
	@NotBlank
	@JsonProperty("salary")
	private Double salary;
	@NotBlank
	@JsonProperty("gender")
	private String gender;
	@NotBlank
	@JsonProperty("team")
	private String team;
	@NotBlank
	@JsonProperty("isDeleted")
	private boolean isDeleted;

}
