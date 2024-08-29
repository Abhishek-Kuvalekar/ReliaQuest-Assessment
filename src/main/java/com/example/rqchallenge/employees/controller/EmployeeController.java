package com.example.rqchallenge.employees.controller;

import com.example.rqchallenge.employees.connector.EmployeeConnector;
import com.example.rqchallenge.employees.model.Employee;
import com.example.rqchallenge.employees.service.IEmployeeService;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
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
    IEmployeeService employeeService;

    @Override
    public Single<ResponseEntity<List<Employee>>> getAllEmployees() throws IOException {
        Map<String, String> contextMap = MDC.getCopyOfContextMap();
        return Single.fromCallable(() -> {
            MDC.setContextMap(contextMap);
            return ResponseEntity.ok(employeeService.getAllEmployees());
        }).subscribeOn(Schedulers.io()).doFinally(MDC::clear);
    }

    @Override
    public Single<ResponseEntity<List<Employee>>> getEmployeesByNameSearch(String searchString) {
        Map<String, String> contextMap = MDC.getCopyOfContextMap();
        return Single.fromCallable(() -> {
            MDC.setContextMap(contextMap);
            return ResponseEntity.ok(employeeService.getEmployeesByNameSearch(searchString));
        }).subscribeOn(Schedulers.io()).doFinally(MDC::clear);
    }

    @Override
    public Single<ResponseEntity<Employee>> getEmployeeById(String id) {
        Map<String, String> contextMap = MDC.getCopyOfContextMap();
        return Single.fromCallable(() -> {
            MDC.setContextMap(contextMap);
            return ResponseEntity.ok(employeeService.getEmployeeById(id));
        }).subscribeOn(Schedulers.io()).doFinally(MDC::clear);
    }

    @Override
    public Single<ResponseEntity<Integer>> getHighestSalaryOfEmployees() {
        Map<String, String> contextMap = MDC.getCopyOfContextMap();
        return Single.fromCallable(() -> {
            MDC.setContextMap(contextMap);
            return ResponseEntity.ok(employeeService.getHighestSalaryOfEmployee());
        }).subscribeOn(Schedulers.io()).doFinally(MDC::clear);
    }

    @Override
    public Single<ResponseEntity<List<String>>> getTopTenHighestEarningEmployeeNames() {
        Map<String, String> contextMap = MDC.getCopyOfContextMap();
        return Single.fromCallable(() -> {
            MDC.setContextMap(contextMap);
            return ResponseEntity.ok(employeeService.getTopTenHighestEarningEmployeeNames());
        }).subscribeOn(Schedulers.io()).doFinally(MDC::clear);
    }

    @Override
    public Single<ResponseEntity<Employee>> createEmployee(Map<String, Object> employeeInput) {
        Map<String, String> contextMap = MDC.getCopyOfContextMap();
        return Single.fromCallable(() -> {
            MDC.setContextMap(contextMap);
            return ResponseEntity.ok(employeeService.createEmployee(employeeInput));
        }).subscribeOn(Schedulers.io()).doFinally(MDC::clear);
    }

    @Override
    public Single<ResponseEntity<String>> deleteEmployeeById(String id) {
        Map<String, String> contextMap = MDC.getCopyOfContextMap();
        return Single.fromCallable(() -> {
            MDC.setContextMap(contextMap);
            return ResponseEntity.ok(employeeService.deleteEmployeeById(id));
        }).subscribeOn(Schedulers.io()).doFinally(MDC::clear);
    }
}
