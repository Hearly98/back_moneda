package com.moneda.user_microservice.utils;

import java.util.Arrays;
import java.util.Optional;

public enum NationalityCode {

    ARGENTINA("AR", "argUserServiceImpl");

    private final String code;
    private final String strategyBeanName;

    NationalityCode(String code, String strategyBeanName) {
        this.code = code;
        this.strategyBeanName = strategyBeanName;
    }

    public String getCode() {
        return code;
    }

    public String getStrategyBeanName() {
        return strategyBeanName;
    }

    public static Optional<NationalityCode> fromCode(String code) {
        return Arrays.stream(values())
                .filter(c -> c.getCode().equalsIgnoreCase(code))
                .findFirst();
    }
}
