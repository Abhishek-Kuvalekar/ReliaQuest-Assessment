package com.example.rqchallenge.employees.connector.model.response;

import com.example.rqchallenge.employees.model.Employee;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class GetEmployeeByIdResponse extends BaseResponse {
    private Employee data;
}
