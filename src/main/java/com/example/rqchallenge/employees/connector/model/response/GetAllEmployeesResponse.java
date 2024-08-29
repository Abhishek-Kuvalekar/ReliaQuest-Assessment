package com.example.rqchallenge.employees.connector.model.response;

import com.example.rqchallenge.employees.model.Employee;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class GetAllEmployeesResponse extends BaseResponse {
    private List<Employee> data;
}
