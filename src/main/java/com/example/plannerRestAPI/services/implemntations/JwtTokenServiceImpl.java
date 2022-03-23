package com.example.plannerRestAPI.services.implemntations;

import com.example.plannerRestAPI.security.UserAppDetails;
import com.example.plannerRestAPI.services.JwtTokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class JwtTokenServiceImpl implements JwtTokenService {

    private final static SecretKey SECRET_KEY = Keys.hmacShaKeyFor(Decoders.BASE64.decode("fasdasdas213414312csdfASDASDS74756765ASCVBE34534543ZXZXCZXCFGHFGHFGhfgsd4334azxczxAASDASD"));
    private final static int TOKEN_DURATION = 90000;

    @Override
    public String createToken(UserAppDetails userAppDetails, String username) {

        Map<String, Object> claims = new HashMap<>();
        Date tokenStartTime = new Date(System.currentTimeMillis());
        Date tokenExpirationTime = new Date(System.currentTimeMillis() + TOKEN_DURATION);

        List<String> authorities = userAppDetails
                .getAuthorities()
                .stream()
                .map(role -> role.getAuthority())
                .collect(Collectors.toList());
        claims.put("authorities", authorities);

        String jwtToken = Jwts
                .builder()
                .setHeaderParam("typ", "JWT")
                .setSubject(username)
                .setIssuedAt(tokenStartTime)
                .setExpiration(tokenExpirationTime)
                .signWith(SECRET_KEY)
                .addClaims(claims)
                .compact();

        return jwtToken;
    }

    @Override
    public String updateTokenExpirationDate(String jwtToken) {
        return null;
    }

    @Override
    public Boolean validateToken(UserAppDetails userAppDetails, String jwtToken) {

        System.out.println(userAppDetails.getUsername());

        if(!userAppDetails.getUsername().equals("catalin2")) throw new JwtException("Token invalid");

        try{
            isTokenExpired(jwtToken);
        } catch (ExpiredJwtException expiredJwtException) {
            throw expiredJwtException;
        }

        return true;
    }

    @Override
    public Boolean isTokenExpired(String jwtToken) {
        return extractExpirationDate(jwtToken).before(new Date());
    }

    @Override
    public String extractUsername(String jwtToken) {
        return extractClaims(jwtToken, Claims::getSubject);
    }

    @Override
    public Date extractExpirationDate(String jwtToken) {
        return extractClaims(jwtToken, Claims::getExpiration);
    }

    @Override
    public <T> T extractClaims(String jwtToken, Function<Claims, T> claimsResolver) {
        Claims claims = extractAllClaims(jwtToken);
        return claimsResolver.apply(claims);
    }

    @Override
    public Claims extractAllClaims(String jwtToken) {
        return Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(jwtToken).getBody();
    }
}
