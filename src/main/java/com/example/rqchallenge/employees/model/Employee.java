package com.example.rqchallenge.employees.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;

@Data
public class Employee {
    @JsonProperty("id")
    private int id;

    @JsonProperty("employee_name")
    private String name;

    @JsonProperty("employee_salary")
    private int salary;

    @JsonProperty("employee_age")
    private int age;

    @JsonProperty(value = "profile_image", defaultValue = "")

    private String image;
}
