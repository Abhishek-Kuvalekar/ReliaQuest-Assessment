package com.example.rqchallenge.employees.model.request;

import lombok.Data;

@Data
public class CreateEmployeeRequest {
    private String name;
    private String salary;
    private String age;
}
