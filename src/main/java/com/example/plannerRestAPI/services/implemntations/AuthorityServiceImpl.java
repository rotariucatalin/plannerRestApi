package com.example.plannerRestAPI.services.implemntations;

import com.example.plannerRestAPI.dtos.AuthorityDTO;
import com.example.plannerRestAPI.repositories.AuthorityRepository;
import com.example.plannerRestAPI.services.AuthorityService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthorityServiceImpl implements AuthorityService {

    private final AuthorityRepository authorityRepository;

    public AuthorityServiceImpl(AuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
    }

    @Override
    public List<AuthorityDTO> findAll() {

        List<AuthorityDTO> authorityDTOS = authorityRepository.findAll().stream().map(authority -> new AuthorityDTO(authority.getId(), authority.getName())).collect(Collectors.toList());

        return authorityDTOS;
    }

    @Override
    public Optional<AuthorityDTO> findAuthority(int id) {

        Optional<AuthorityDTO> authority = authorityRepository.findById(id).map(auth -> new AuthorityDTO(auth.getId(), auth.getName()));

        return authority;
    }
}
