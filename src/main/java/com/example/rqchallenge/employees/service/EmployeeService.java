package com.example.rqchallenge.employees.service;

import com.example.rqchallenge.employees.connector.EmployeeConnector;
import com.example.rqchallenge.employees.constant.Constants;
import com.example.rqchallenge.employees.exception.ApiException;
import com.example.rqchallenge.employees.exception.BadRequestException;
import com.example.rqchallenge.employees.model.Employee;
import com.example.rqchallenge.employees.model.request.CreateEmployeeRequest;
import com.example.rqchallenge.employees.model.response.CreateEmployeeResponse;
import com.example.rqchallenge.employees.model.response.DeleteEmployeeResponse;
import com.example.rqchallenge.employees.model.response.GetAllEmployeesResponse;
import com.example.rqchallenge.employees.model.response.GetEmployeeByIdResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.jar.JarException;
import java.util.stream.Collectors;

@Slf4j
@Service
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class EmployeeService implements IEmployeeService {
    @Autowired
    EmployeeConnector employeeConnector;

    @Override
    public List<Employee> getAllEmployees() {
        GetAllEmployeesResponse response = employeeConnector.getAllEmployees();
        if (!Constants.STATUS_SUCCESS.equalsIgnoreCase(response.getStatus())) {
            log.error("Get all employees operation failed. Message: {}", response.getMessage());
            throw new ApiException(response.getMessage());
        }
        return response.getData();
    }

    @Override
    public List<Employee> getEmployeesByNameSearch(String searchString) {
        String searchStringInLowerCase = searchString.toLowerCase();
        List<Employee> employees = getAllEmployees();
        return employees.stream()
                .filter(
                        employee -> employee
                                        .getName()
                                        .toLowerCase()
                                        .contains(searchStringInLowerCase)
                )
                .collect(Collectors.toList());
    }

    @Override
    public Employee getEmployeeById(String id) {
        GetEmployeeByIdResponse response = employeeConnector.getEmployeeById(id);
        if (!Constants.STATUS_SUCCESS.equalsIgnoreCase(response.getStatus())) {
            log.error("Get employee by id operation failed. Message: {}", response.getMessage());
            throw new ApiException(response.getMessage());
        }
        return response.getData();
    }

    @Override
    public Integer getHighestSalaryOfEmployee() {
        List<Employee> employees = getAllEmployees();
        return employees.stream()
                .map(Employee::getSalary)
                .max(Comparator.comparingInt(i -> i))
                .orElse(0);
    }

    @Override
    public List<String> getTopTenHighestEarningEmployeeNames() {
        List<Employee> employees = getAllEmployees();
        return employees.stream()
                .sorted(Comparator.comparingInt(Employee::getSalary).reversed())
                .limit(10)
                .map(Employee::getName)
                .collect(Collectors.toList());
    }

    @Override
    public Employee createEmployee(Map<String, Object> employeeInput) {
        boolean isRequestValid = validateCreateEmployeeRequest(employeeInput);
        if (!isRequestValid) throw new BadRequestException();

        CreateEmployeeRequest request = new CreateEmployeeRequest();
        request.setName((String) employeeInput.get(Constants.MAP_KEY_NAME));
        request.setAge((String) employeeInput.get(Constants.MAP_KEY_AGE));
        request.setSalary((String) employeeInput.get(Constants.MAP_KEY_SALARY));

        try {
            CreateEmployeeResponse response = employeeConnector.createEmployee(request);

            if (!Constants.STATUS_SUCCESS.equalsIgnoreCase(response.getStatus())) {
                log.error("Create employee operation failed. Message: {}", response.getMessage());
                throw new ApiException(response.getMessage());
            }
            return response.getData();
        } catch (Exception ex) {
            log.error("Create employee failed with an exception.", ex);
            throw new ApiException(ex.getMessage());
        }
    }

    @Override
    public String deleteEmployeeById(String id) {
        try {
            DeleteEmployeeResponse response = employeeConnector.deleteEmployee(id);
            if (!Constants.STATUS_SUCCESS.equalsIgnoreCase(response.getStatus())) {
                log.error("Delete employee by id operation failed. Message: {}", response.getMessage());
                throw new ApiException(response.getMessage());
            }
            return response.getMessage();
        } catch (Exception ex) {
            log.error("Delete employee by id failed with an exception.", ex);
            throw new ApiException(ex.getMessage());
        }
    }

    private boolean validateCreateEmployeeRequest(Map<String, Object> employeeInput) {
        if (Objects.isNull(employeeInput)) return false;

        try {
            String name = (String) employeeInput.getOrDefault(Constants.MAP_KEY_NAME, Constants.EMPTY_STRING);
            if (name.isEmpty()) return false;

            Integer age = (Integer) employeeInput.getOrDefault(Constants.MAP_KEY_AGE, Constants.EMPTY_STRING);
            if (age < 18 || age > 60) return false;

            Integer salary = (Integer) employeeInput.getOrDefault(Constants.MAP_KEY_SALARY, Constants.EMPTY_STRING);
            if (salary <= 0) return false;
        } catch (Exception ex) {
            return false;
        }

        return true;
    }
}
