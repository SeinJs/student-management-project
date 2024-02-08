package com.nhnacademy.student.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@PropertySource("classpath:common.properties")
public class CommonPropertiesConfig {
    @Value("#{'${excludeUrls}'.split(',')}")
    private Set<String> excludeUrls;

    public Set<String> getExcludeUrls() {
        return excludeUrls;
    }
}
