package com.example.plannerRestAPI.controllers;

import com.example.plannerRestAPI.exceptions.ApiRequestException;
import com.example.plannerRestAPI.models.AuthenticationRequest;
import com.example.plannerRestAPI.models.AuthenticationResponse;
import com.example.plannerRestAPI.security.UserAppDetails;
import com.example.plannerRestAPI.services.JwtTokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
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

import java.security.Principal;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping(value = "/authentication")
public class AuthenticationController {


    private final AuthenticationManager authenticationManager;
    private final JwtTokenService jwtTokenService;

    public AuthenticationController(AuthenticationManager authenticationManager, JwtTokenService jwtTokenService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenService = jwtTokenService;
    }

    @PostMapping
    public ResponseEntity<AuthenticationResponse> generateToken(@RequestBody AuthenticationRequest authenticationRequest) throws ApiRequestException {

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        String token;
        try {
            Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
            token = jwtTokenService.createToken((UserAppDetails) authentication.getPrincipal(), authentication.getName());
        } catch (AuthenticationException authenticationException) {
            throw new ApiRequestException(authenticationException.getMessage());
        }

        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        authenticationResponse.setToken(token);

        return new ResponseEntity<>(authenticationResponse, HttpStatus.OK);
    }

    @PostMapping(value = "/regenerateToken")
    public ResponseEntity<AuthenticationResponse> regenerateToken(@RequestBody AuthenticationRequest authenticationRequest, Principal principal) throws ApiRequestException {

        if(!authenticationRequest.getToken().equals("")) {
            try{

                Claims claims = jwtTokenService.extractAllClaims(authenticationRequest.getToken());
                long duration  = claims.getExpiration().getTime() - new Date().getTime();
                long diffInMinutes = TimeUnit.MILLISECONDS.toMinutes(duration);

                if(diffInMinutes <= 1) {
                    throw new ApiRequestException("Permission denied with this token!");
                }

                jwtTokenService.validateToken(principal, authenticationRequest.getToken());
                String updateTokenExpirationDate = jwtTokenService.updateTokenExpirationDate(authenticationRequest.getToken());

                AuthenticationResponse authenticationResponse = new AuthenticationResponse();
                authenticationResponse.setToken(updateTokenExpirationDate);

                return new ResponseEntity<>(authenticationResponse, HttpStatus.OK);

            } catch (ExpiredJwtException e) {
                throw new ApiRequestException("Token Expired!");
            } catch (JwtException jw) {
                throw new ApiRequestException(jw.getMessage());
            } catch (Exception exception) {
                throw new ApiRequestException(exception.getMessage());
            }
        } else {
            throw new ApiRequestException("Permission denied");
        }
    }
}
