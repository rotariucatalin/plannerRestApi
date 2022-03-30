package com.example.plannerRestAPI.controllers;

import com.example.plannerRestAPI.dtos.UserAuthorityDTO;
import com.example.plannerRestAPI.dtos.UserDTO;
import com.example.plannerRestAPI.exceptions.ApiRequestException;
import com.example.plannerRestAPI.models.CustomApiResponse;
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
    public ResponseEntity<CustomApiResponse> deleteUser(@PathVariable(value = "id") int id) throws ApiRequestException {

        userAppService.deleteUser(id);
        CustomApiResponse customApiResponse = new CustomApiResponse("User deleted successfully!", OK);

        return new ResponseEntity<>(customApiResponse, OK);
    }

    @GetMapping(value = "/getAuthorityForUser/{id}")
    public ResponseEntity<UserAuthorityDTO> getAuthorityForUser(@PathVariable(value = "id") int id) throws ApiRequestException {

        UserAuthorityDTO userAuthorityDTO = userAppService.getAuthorityForUser(id);

        return new ResponseEntity<>(userAuthorityDTO, OK);
    }
}
