package com.example.plannerRestAPI.services;

import com.example.plannerRestAPI.dtos.AuthorityDTO;
import com.example.plannerRestAPI.dtos.UserAuthorityDTO;
import com.example.plannerRestAPI.dtos.UserDTO;
import com.example.plannerRestAPI.entities.User;
import com.example.plannerRestAPI.exceptions.ApiRequestException;

import java.util.List;
import java.util.Optional;

public interface UserAppService {

    List<UserDTO> getAllUsers();
    UserDTO getUser(Integer id) throws ApiRequestException;
    UserDTO addNewUser(UserDTO userDTO) throws ApiRequestException;
    UserDTO updateUser(Integer id, UserDTO userDTO) throws ApiRequestException;
    Optional<User> findByUsername(String username);
    void deleteUser(Integer id) throws ApiRequestException;
    void removeAllAuthorityForUser(Integer id) throws ApiRequestException;
    UserAuthorityDTO getAuthorityForUser(Integer id) throws ApiRequestException;
    List<AuthorityDTO> addAuthorityForUser(Integer id, List<AuthorityDTO> authorityDTOS) throws ApiRequestException;
    List<AuthorityDTO> updateAuthorityForUser(Integer id, List<AuthorityDTO> authorityDTOS) throws ApiRequestException;
}
