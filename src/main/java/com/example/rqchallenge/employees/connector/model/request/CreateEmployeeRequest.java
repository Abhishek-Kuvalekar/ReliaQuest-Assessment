package com.example.rqchallenge.employees.connector.model.request;

import lombok.Data;

@Data
public class CreateEmployeeRequest {
    private String name;
    private String salary;
    private String age;
}
