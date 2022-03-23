package com.example.plannerRestAPI.services.implemntations;

import com.example.plannerRestAPI.entities.User;
import com.example.plannerRestAPI.repositories.UserAppRepository;
import com.example.plannerRestAPI.services.UserAppService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserAppServiceImpl implements UserAppService {

    private final UserAppRepository userAppRepository;

    public UserAppServiceImpl(UserAppRepository userAppRepository) {
        this.userAppRepository = userAppRepository;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userAppRepository.findByUsername(username);
    }
}
