package com.example.rqchallenge.employees.cache;

import com.example.rqchallenge.employees.model.Employee;
import com.example.rqchallenge.employees.properties.EmployeeCacheProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class EmployeeCache {
    @Autowired
    EmployeeCacheProperties cacheProperties;

    private ConcurrentMap<String, Employee> map = new ConcurrentHashMap<>();
    private AtomicLong lastLoadTime = new AtomicLong(0);
    private AtomicBoolean isCacheLoaded = new AtomicBoolean(false);
    private final List<Employee> emptyList = Collections.unmodifiableList(new ArrayList<>());

    public void loadCache(List<Employee> employees) {
        if (isCacheValid()) return;

        isCacheLoaded.set(false);
        map.clear();

        for (Employee employee : employees) {
            map.put(String.valueOf(employee.getId()), employee);
        }

        lastLoadTime.set(System.currentTimeMillis());
        isCacheLoaded.set(true);
    }

    public List<Employee> getAllEmployees() {
        if (!isCacheValid()) return emptyList;
        return List.copyOf(map.values());
    }

    public Optional<Employee> getEmployeeById(String id) {
        if (!isCacheValid()) return Optional.empty();
        if (!map.containsKey(id)) return Optional.empty();
        return Optional.of(map.get(id));
    }

    public void addEmployee(Employee employee) {
        if (Objects.isNull(employee)) return;
        if (!isCacheValid()) return;
        map.put(String.valueOf(employee.getId()), employee);
    }

    public void removeEmployee(String id) {
        if (!isCacheValid()) return;
        map.remove(id);
    }

    private boolean isCacheValid() {
        long currentTime = System.currentTimeMillis();
        long loadTime = lastLoadTime.get();
        return (((currentTime - loadTime) < cacheProperties.getTtl()) && isCacheLoaded.get());
    }
}
