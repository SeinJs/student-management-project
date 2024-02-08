package com.nhnacademy.student.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value = "classpath:/db.properties")
public class DatabaseProperties {
    @Value("${db.username}")
    private String username;
    @Value("${db.password}")
    private String password;
    @Value("${db.driverClassName}")
    private String driverClassName;
    @Value("${db.url}")
    private String url;
    @Value("${db.initialSize}")
    private Integer initialSize;
    @Value("${db.maxTotal}")
    private Integer maxTotal;
    @Value("${db.maxIdle}")
    private Integer maxIdle;
    @Value("${db.minIdle}")
    private Integer minIdle;
    @Value("${db.maxWaitMillis}")
    private Integer maxWaitMillis;
    @Value("${db.validationQuery}")
    private String validationQuery;
    @Value("${db.testOnBorrow}")
    private boolean testOnBorrow;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public String getUrl() {
        return url;
    }

    public Integer getInitialSize() {
        return initialSize;
    }

    public Integer getMaxTotal() {
        return maxTotal;
    }

    public Integer getMaxIdle() {
        return maxIdle;
    }

    public Integer getMinIdle() {
        return minIdle;
    }

    public Integer getMaxWaitMillis() {
        return maxWaitMillis;
    }

    public String getValidationQuery() {
        return validationQuery;
    }

    public boolean isTestOnBorrow() {
        return testOnBorrow;
    }
}
