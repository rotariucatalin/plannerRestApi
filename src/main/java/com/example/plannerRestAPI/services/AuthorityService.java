package com.example.plannerRestAPI.services;

import com.example.plannerRestAPI.dtos.AuthorityDTO;
import com.example.plannerRestAPI.dtos.UserAuthorityDTO;
import com.example.plannerRestAPI.dtos.UserDTO;
import com.example.plannerRestAPI.entities.Authority;
import com.example.plannerRestAPI.exceptions.ApiRequestException;

import java.util.List;
import java.util.Optional;

public interface AuthorityService {

    List<AuthorityDTO> findAll();
    Optional<AuthorityDTO> findAuthority(int id);
    AuthorityDTO addNewAuthority(AuthorityDTO authorityDTO) throws ApiRequestException;
    AuthorityDTO updateAuthority(AuthorityDTO authorityDTO) throws ApiRequestException;
    Authority findAuthoritiesById(Integer id);
    List<UserAuthorityDTO> getAllUsersForAuthority(Integer id) throws ApiRequestException;
    void deleteAuthority(int id) throws ApiRequestException;
    List<UserAuthorityDTO> updateAuthorityForUser(Integer id, List<UserDTO> userDTO) throws ApiRequestException;
    void removeAuthorityForAllUsers(Integer id) throws ApiRequestException;
    void addAuthorityForAllUsers(Integer id) throws ApiRequestException;
}
