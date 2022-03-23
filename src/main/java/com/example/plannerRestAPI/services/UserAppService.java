package com.example.plannerRestAPI.services;

import com.example.plannerRestAPI.entities.User;

import java.util.Optional;

public interface UserAppService {

    Optional<User> findByUsername(String username);

}
