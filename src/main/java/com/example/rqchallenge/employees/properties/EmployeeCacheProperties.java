package com.example.rqchallenge.employees.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Data
@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public final class EmployeeCacheProperties {
    @Value("${employee.config.cache.ttl}")
    private long ttl;
}
