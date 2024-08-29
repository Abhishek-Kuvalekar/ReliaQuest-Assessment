package com.example.rqchallenge.employees.connector;

import com.example.rqchallenge.employees.connector.model.request.CreateEmployeeRequest;
import com.example.rqchallenge.employees.connector.model.response.CreateEmployeeResponse;
import com.example.rqchallenge.employees.connector.model.response.DeleteEmployeeResponse;
import com.example.rqchallenge.employees.connector.model.response.GetAllEmployeesResponse;
import com.example.rqchallenge.employees.connector.model.response.GetEmployeeByIdResponse;
import com.example.rqchallenge.employees.constant.Constants;
import com.example.rqchallenge.employees.properties.EmployeeProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class EmployeeConnector {
    @Autowired
    HttpClient httpClient;

    @Autowired
    EmployeeProperties employeeProperties;

    public GetAllEmployeesResponse getAllEmployees() {
        String url = String.format(
                "%s%s",
                employeeProperties.getBaseUrl(),
                employeeProperties.getGetAllEmployeesPath()
        );
        return httpClient.get(url, GetAllEmployeesResponse.class);
    }

    public GetEmployeeByIdResponse getEmployeeById(String id) {
        String url = String.format(
                "%s%s",
                employeeProperties.getBaseUrl(),
                employeeProperties.getGetEmployeeByIdPath().replace(Constants.ID, id)
        );
        return httpClient.get(url, GetEmployeeByIdResponse.class);
    }

    public CreateEmployeeResponse createEmployee(CreateEmployeeRequest request) throws JsonProcessingException {
        String url = String.format(
                "%s%s",
                employeeProperties.getBaseUrl(),
                employeeProperties.getCreateEmployeePath()
        );
        return httpClient.post(url, request, CreateEmployeeResponse.class);
    }

    public DeleteEmployeeResponse deleteEmployee(String id) throws JsonProcessingException {
        String url = String.format(
                "%s%s",
                employeeProperties.getBaseUrl(),
                employeeProperties.getDeleteEmployeePath().replace(Constants.ID, id)
        );
        return httpClient.delete(url, null, DeleteEmployeeResponse.class);
    }
}
