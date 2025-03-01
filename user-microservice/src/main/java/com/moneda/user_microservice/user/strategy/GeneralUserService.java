package com.moneda.user_microservice.user.strategy;

import com.moneda.user_microservice.user.entity.User;
import org.springframework.stereotype.Service;

@Service("generalUserServiceImpl")
public class GeneralUserService implements UserCountryService{
    @Override
    public User createUser(User user) {
        return user;
    }
}
