package com.example.plannerRestAPI.security;

import com.example.plannerRestAPI.entities.User;
import com.example.plannerRestAPI.services.UserAppService;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserAppDetailsService implements UserDetailsService {

    private final UserAppService userAppService;

    public UserAppDetailsService(UserAppService userAppService) {
        this.userAppService = userAppService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> userFound = userAppService.findByUsername(username);
        UserAppDetails userAppDetails = new UserAppDetails( userFound.orElseThrow(() -> new BadCredentialsException("Bad credentials!")) );

        return userAppDetails;
    }
}
