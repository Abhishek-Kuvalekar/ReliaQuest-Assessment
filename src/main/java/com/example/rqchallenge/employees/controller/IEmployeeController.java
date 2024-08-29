package com.example.rqchallenge.employees.controller;

import com.example.rqchallenge.employees.model.Employee;
import io.reactivex.rxjava3.core.Single;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public interface IEmployeeController {

    @GetMapping()
    Single<ResponseEntity<List<Employee>>> getAllEmployees();

    @GetMapping("/search/{searchString}")
    Single<ResponseEntity<List<Employee>>> getEmployeesByNameSearch(@PathVariable String searchString);

    @GetMapping("/{id}")
    Single<ResponseEntity<Employee>> getEmployeeById(@PathVariable String id);

    @GetMapping("/highestSalary")
    Single<ResponseEntity<Integer>> getHighestSalaryOfEmployees();

    @GetMapping("/topTenHighestEarningEmployeeNames")
    Single<ResponseEntity<List<String>>> getTopTenHighestEarningEmployeeNames();

    @PostMapping()
    Single<ResponseEntity<Employee>> createEmployee(@RequestBody Map<String, Object> employeeInput);

    @DeleteMapping("/{id}")
    Single<ResponseEntity<String>> deleteEmployeeById(@PathVariable String id);

}
