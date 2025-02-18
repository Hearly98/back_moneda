package com.moneda.user_microservice.common.user.repository;

import com.moneda.user_microservice.common.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    List<User> findByIsActiveTrue();

}
