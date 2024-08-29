package com.example.rqchallenge.employees.connector.model.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CreateEmployeeResponse extends BaseResponse {
    private CreateEmployeeResponseData data;

    @Data
    public static class CreateEmployeeResponseData {
        private int id;
        private String name;
        private String age;
        private String salary;
    }
}
