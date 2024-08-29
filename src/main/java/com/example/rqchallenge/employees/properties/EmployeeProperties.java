package com.example.rqchallenge.employees.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Data
@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public final class EmployeeProperties {
    @Value("${employee.config.base-url}")
    private String baseUrl;

    @Value("${employee.config.paths.get-all-employees}")
    private String getAllEmployeesPath;

    @Value("${employee.config.paths.get-employee-by-id}")
    private String getEmployeeByIdPath;

    @Value("${employee.config.paths.create-employee}")
    private String createEmployeePath;

    @Value("${employee.config.paths.delete-employee}")
    private String deleteEmployeePath;
}
