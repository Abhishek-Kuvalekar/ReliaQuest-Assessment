package com.example.rqchallenge.employees.model.response;

import com.example.rqchallenge.employees.model.Employee;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CreateEmployeeResponse extends BaseResponse {
    private Employee data;
}
