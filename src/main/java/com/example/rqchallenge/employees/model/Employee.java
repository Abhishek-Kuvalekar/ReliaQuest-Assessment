package com.example.rqchallenge.employees.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@ToString
public class Employee {
    @JsonSetter("id")
    @Getter(onMethod_ = @JsonGetter("id"))
    private int id;

    @JsonSetter("employee_name")
    @Getter(onMethod_ = @JsonGetter("name"))
    private String name;

    @JsonSetter("employee_salary")
    @Getter(onMethod_ = @JsonGetter("salary"))
    private int salary;

    @JsonSetter("employee_age")
    @Getter(onMethod_ = @JsonGetter("age"))
    private int age;

    @JsonSetter(value = "profile_image")
    @Getter(onMethod_ = @JsonGetter("profile_image"))
    private String image;
}
