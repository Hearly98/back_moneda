package com.moneda.user_microservice.common.user.context;

import com.moneda.user_microservice.common.user.entity.User;
import com.moneda.user_microservice.common.user.strategy.UserCountryService;
import com.moneda.user_microservice.common.utils.NationalityCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class UserCreationContext {
    private final Map<String, UserCountryService> services;
    private final UserCountryService generalService;

    @Autowired
    public UserCreationContext(Map<String, UserCountryService> services) {
        this.services = services;
        this.generalService = services.get("generalUserServiceImpl");
    }

    public User createUser(String nationality, User user) {
        // Buscamos el servicio correspondiente
        String serviceBeanName = NationalityCode.fromCode(nationality)
                .map(NationalityCode::getStrategyBeanName)
                .orElse("generalUserServiceImpl");
        UserCountryService service = services.getOrDefault(serviceBeanName, generalService);
        return service.createUser(user);
    }
}
