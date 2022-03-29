package com.example.plannerRestAPI.controllers;

import com.example.plannerRestAPI.dtos.AuthorityDTO;
import com.example.plannerRestAPI.dtos.UserAuthorityDTO;
import com.example.plannerRestAPI.exceptions.ApiRequestException;
import com.example.plannerRestAPI.models.CustomApiResponse;
import com.example.plannerRestAPI.services.AuthorityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(value = "/authority")
public class AuthorityController {

    private final AuthorityService authorityService;

    public AuthorityController(AuthorityService authorityService) {
        this.authorityService = authorityService;
    }

    @GetMapping
    public ResponseEntity<List<AuthorityDTO>> getAllAuthorities() {

        List<AuthorityDTO> authorityList = authorityService.findAll();
        return new ResponseEntity<>(authorityList, OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<AuthorityDTO> getAuthority(@PathVariable(value = "id") int id) throws ApiRequestException {
        return new ResponseEntity<>(authorityService.findAuthority(id).orElseThrow( () -> new ApiRequestException("No authority found!")), OK);
    }

    @PostMapping
    public ResponseEntity<AuthorityDTO> addNewAuthority(@RequestBody AuthorityDTO authorityDTO) throws ApiRequestException {

        AuthorityDTO addNewAuthority = authorityService.addNewAuthority(authorityDTO);
        return new ResponseEntity<>(addNewAuthority, OK);
    }

    @PutMapping
    public ResponseEntity<AuthorityDTO> updateAuthority(@RequestBody AuthorityDTO authorityDTO) throws ApiRequestException {

        AuthorityDTO updatedAuthority = authorityService.updateAuthority(authorityDTO);
        return new ResponseEntity<>(updatedAuthority, OK);
    }

    @DeleteMapping(value = "/deleteAuthority/{id}")
    public ResponseEntity<CustomApiResponse> deleteAuthority(@PathVariable(value = "id") int id) throws ApiRequestException {

        authorityService.findAuthority(id).orElseThrow( () -> new ApiRequestException("Authority not found!") );
        authorityService.deleteAuthority(id);

        CustomApiResponse customApiResponse = new CustomApiResponse("Authority deleted!", OK);

        return new ResponseEntity<>(customApiResponse, OK);
    }

    @GetMapping(value = "/getAllUsersForAuthority/{id}")
    public ResponseEntity<List<UserAuthorityDTO>> getAllUsersForAuthority(@PathVariable(value = "id") int id) throws ApiRequestException {

        List<UserAuthorityDTO> authorityDTOS = authorityService.getAllUsersForAuthority(id);
        return new ResponseEntity<>(authorityDTOS, OK);
    }

    @PutMapping(value = "/updateAuthorityForUser/{id}")
    public ResponseEntity<List<UserAuthorityDTO>> updateAuthorityForUser(@PathVariable(value = "id") int id) throws ApiRequestException {

        List<UserAuthorityDTO> userAuthorityList = authorityService.updateAuthorityForUser(id);
        return new ResponseEntity<>(userAuthorityList, OK);
    }

    @PutMapping(value = "/removeAuthorityForAllUsers/{id}")
    public ResponseEntity<CustomApiResponse> removeAuthorityForAllUsers(@PathVariable(name = "id") int id) throws ApiRequestException {

        authorityService.removeAuthorityForAllUsers(id);
        CustomApiResponse customApiResponse = new CustomApiResponse("Authority removed for all users", OK);

        return new ResponseEntity<>(customApiResponse, OK);
    }

    @PutMapping(value = "/addAuthorityForAllUsers/{id}")
    public ResponseEntity<CustomApiResponse> addAuthorityForAllUsers(@PathVariable(name = "id") int id) {

        /*
        TODO make this method after the user functionality is added!
         */

        return null;
    }
}
