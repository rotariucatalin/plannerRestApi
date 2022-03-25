package com.example.plannerRestAPI.controllers;

import com.example.plannerRestAPI.dtos.AuthorityDTO;
import com.example.plannerRestAPI.exceptions.ApiRequestException;
import com.example.plannerRestAPI.services.AuthorityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping(value = "/authority")
public class AuthorityController {

    private final AuthorityService authorityService;

    public AuthorityController(AuthorityService authorityService) {
        this.authorityService = authorityService;
    }

    @GetMapping
    public ResponseEntity<?> getAllAuthorities() {
        
        List<AuthorityDTO> authorityList = authorityService.findAll();
        return new ResponseEntity<>(authorityList, OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getAuthority(@PathVariable(value = "id") int id) throws ApiRequestException {
        return new ResponseEntity<>(authorityService.findAuthority(id).orElseThrow( () -> new ApiRequestException("No authority found!")), OK);
    }
}
