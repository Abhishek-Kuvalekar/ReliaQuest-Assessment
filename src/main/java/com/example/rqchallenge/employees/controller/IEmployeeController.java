package com.example.rqchallenge.employees.controller;

import com.example.rqchallenge.employees.model.Employee;
import io.reactivex.rxjava3.core.Flowable;
import org.reactivestreams.Publisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
public interface IEmployeeController {

    @GetMapping()
    Publisher<ResponseEntity<List<Employee>>> getAllEmployees() throws IOException;

    @GetMapping("/search/{searchString}")
    Publisher<ResponseEntity<List<Employee>>> getEmployeesByNameSearch(@PathVariable String searchString);

    @GetMapping("/{id}")
    Publisher<ResponseEntity<Employee>> getEmployeeById(@PathVariable String id);

    @GetMapping("/highestSalary")
    Publisher<ResponseEntity<Integer>> getHighestSalaryOfEmployees();

    @GetMapping("/topTenHighestEarningEmployeeNames")
    Publisher<ResponseEntity<List<String>>> getTopTenHighestEarningEmployeeNames();

    @PostMapping()
    Publisher<ResponseEntity<Employee>> createEmployee(@RequestBody Map<String, Object> employeeInput);

    @DeleteMapping("/{id}")
    Publisher<ResponseEntity<String>> deleteEmployeeById(@PathVariable String id);

}
