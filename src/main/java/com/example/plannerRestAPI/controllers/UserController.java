package com.example.plannerRestAPI.controllers;

import com.example.plannerRestAPI.dtos.AuthorityDTO;
import com.example.plannerRestAPI.dtos.UserAuthorityDTO;
import com.example.plannerRestAPI.dtos.UserDTO;
import com.example.plannerRestAPI.exceptions.ApiRequestException;
import com.example.plannerRestAPI.models.ApiResponse;
import com.example.plannerRestAPI.services.UserAppService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    private final UserAppService userAppService;

    public UserController(UserAppService userAppService) {
        this.userAppService = userAppService;
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {

        List<UserDTO> userDTOList = userAppService.getAllUsers();
        return new ResponseEntity<>(userDTOList, OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable(value = "id") int id) throws ApiRequestException {

        UserDTO userDTO = userAppService.getUser(id);
        return new ResponseEntity<>(userDTO, OK);
    }

    @PostMapping
    public ResponseEntity<UserDTO> addNewUser(@RequestBody UserDTO userDTO) throws ApiRequestException {

        UserDTO newUser = userAppService.addNewUser(userDTO);
        return new ResponseEntity<>(newUser, OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable(value = "id") int id, @RequestBody UserDTO userDTO) throws ApiRequestException {

        UserDTO updateUser = userAppService.updateUser(id, userDTO);
        return new ResponseEntity<>(updateUser, OK);
    }

    @DeleteMapping(value = "/deleteUser/{id}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable(value = "id") int id) throws ApiRequestException {

        userAppService.deleteUser(id);
        ApiResponse apiResponse = new ApiResponse("User deleted successfully!", OK);

        return new ResponseEntity<>(apiResponse, OK);
    }

    @GetMapping(value = "/getAuthorityForUser/{id}")
    public ResponseEntity<UserAuthorityDTO> getAuthorityForUser(@PathVariable(value = "id") int id) throws ApiRequestException {

        UserAuthorityDTO userAuthorityDTO = userAppService.getAuthorityForUser(id);

        return new ResponseEntity<>(userAuthorityDTO, OK);
    }

    @PutMapping(value = "/addAuthorityForUser/{id}")
    public ResponseEntity<List<AuthorityDTO>> addAuthorityForUser(@PathVariable(value = "id") int id, @RequestBody List<AuthorityDTO> authorityDTOS) throws ApiRequestException {

        List<AuthorityDTO> addAuthorityForUser = userAppService.addAuthorityForUser(id, authorityDTOS);

        return new ResponseEntity<>(addAuthorityForUser, OK);
    }

    @PutMapping(value = "/updateAuthorityForUser/{id}")
    public ResponseEntity<List<AuthorityDTO>> updateAuthorityForUser(@PathVariable(value = "id") int id, @RequestBody List<AuthorityDTO> authorityDTOS) throws ApiRequestException {

        List<AuthorityDTO> updateAuthorityForUser = userAppService.updateAuthorityForUser(id, authorityDTOS);
        return new ResponseEntity<>(updateAuthorityForUser, OK);
    }

    @PutMapping(value = "/deleteAuthorityForUser/{id}")
    public ResponseEntity<ApiResponse> deleteAuthorityForUser(@PathVariable(value = "id") int id) throws ApiRequestException {

        userAppService.removeAllAuthorityForUser(id);
        ApiResponse apiResponse = new ApiResponse("All authorities are removed!", OK);

        return new ResponseEntity<>(apiResponse, OK);
    }
}
