package com.example.plannerRestAPI.services;

import com.example.plannerRestAPI.dtos.AuthorityDTO;

import java.util.List;
import java.util.Optional;

public interface AuthorityService {

    List<AuthorityDTO> findAll();
    Optional<AuthorityDTO> findAuthority(int id);

}
