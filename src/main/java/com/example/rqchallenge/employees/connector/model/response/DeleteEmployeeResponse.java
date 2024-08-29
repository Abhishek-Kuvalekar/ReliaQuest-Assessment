package com.example.rqchallenge.employees.connector.model.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class DeleteEmployeeResponse extends BaseResponse {
    private String data;
}
