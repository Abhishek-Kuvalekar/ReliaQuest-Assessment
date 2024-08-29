package com.example.rqchallenge.employees.controller;

import com.example.rqchallenge.employees.connector.EmployeeConnector;
import com.example.rqchallenge.employees.model.Employee;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import org.reactivestreams.Publisher;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/employees/v1")
public class EmployeeController implements IEmployeeController {
    @Autowired
    EmployeeConnector client;
    @Override
    public Publisher<ResponseEntity<List<Employee>>> getAllEmployees() throws IOException {
        return Flowable.fromCallable(() -> {
            return ResponseEntity.ok(client.getAllEmployees().getData());
        }).subscribeOn(Schedulers.io()).doFinally(MDC::clear);
    }

    @Override
    public Publisher<ResponseEntity<List<Employee>>> getEmployeesByNameSearch(String searchString) {
        return null;
    }

    @Override
    public Publisher<ResponseEntity<Employee>> getEmployeeById(String id) {
        return null;
    }

    @Override
    public Publisher<ResponseEntity<Integer>> getHighestSalaryOfEmployees() {
        return null;
    }

    @Override
    public Publisher<ResponseEntity<List<String>>> getTopTenHighestEarningEmployeeNames() {
        return null;
    }

    @Override
    public Publisher<ResponseEntity<Employee>> createEmployee(Map<String, Object> employeeInput) {
        return null;
    }

    @Override
    public Publisher<ResponseEntity<String>> deleteEmployeeById(String id) {
        return null;
    }
}
