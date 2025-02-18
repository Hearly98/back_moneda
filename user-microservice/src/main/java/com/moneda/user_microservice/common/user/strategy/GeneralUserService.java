package com.moneda.user_microservice.common.user.strategy;

import com.moneda.user_microservice.common.user.entity.User;
import org.springframework.stereotype.Service;

@Service("generalUserServiceImpl")
public class GeneralUserService implements UserCountryService{
    @Override
    public User createUser(User user) {
        return user;
    }
}
