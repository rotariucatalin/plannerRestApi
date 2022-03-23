package com.example.plannerRestAPI.services;

import com.example.plannerRestAPI.security.UserAppDetails;
import io.jsonwebtoken.Claims;

import java.util.Date;
import java.util.function.Function;

public interface JwtTokenService {

    String createToken(UserAppDetails userAppDetails, String username);
    String updateTokenExpirationDate(String jwtToken);
    Boolean validateToken(UserAppDetails userAppDetails, String jwtToken);
    Boolean isTokenExpired(String jwtToken);
    String extractUsername(String jwtToken);
    Date extractExpirationDate(String jwtToken);
    <T> T extractClaims(String jwtToken, Function<Claims, T> claimsResolver);
    Claims extractAllClaims(String jwtToken);

}
