package com.example.plannerRestAPI.controllers;

import com.example.plannerRestAPI.exceptions.ApiRequestException;
import com.example.plannerRestAPI.models.AuthenticationRequest;
import com.example.plannerRestAPI.models.AuthenticationResponse;
import com.example.plannerRestAPI.security.UserAppDetails;
import com.example.plannerRestAPI.services.JwtTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/authentication")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenService jwtTokenService;

    @PostMapping()
    public ResponseEntity<?> test(@RequestBody AuthenticationRequest authenticationRequest) throws ApiRequestException {

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        String token;
        try {
            Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
            token = jwtTokenService.createToken((UserAppDetails) authentication.getPrincipal(), authentication.getName());
        } catch (AuthenticationException authenticationException) {
            throw new ApiRequestException(authenticationException.getMessage());
        }


//        if(authenticationRequest.getToken() != "") {
//            Claims claims = jwtTokenService.extractAllClaims(authenticationRequest.getToken());
//            //System.out.println(claims.toString());
//        } else {
//            //System.out.println(token);
//        }
//
//        try{
//            jwtTokenService.validateToken((UserAppDetails) authentication.getPrincipal(), token);
//        } catch (ExpiredJwtException e) {
//            System.out.println(e.getMessage());
//
//        } catch (JwtException jw) {
//            System.out.println(jw.getMessage());
//
//        }

        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        authenticationResponse.setToken(token);

        return new ResponseEntity<>(authenticationResponse, HttpStatus.OK);
    }
}
