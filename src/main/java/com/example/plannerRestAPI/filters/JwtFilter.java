package com.example.plannerRestAPI.filters;

import com.example.plannerRestAPI.models.ApiException;
import com.example.plannerRestAPI.services.JwtTokenService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtTokenService jwtTokenService;

    public JwtFilter(JwtTokenService jwtTokenService) {
        this.jwtTokenService = jwtTokenService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException {

        try {
            String authorizationHeader = request.getHeader("Authorization");
            String token = null;
            String username = null;

            if(authorizationHeader.startsWith("GenerateToken")) {

                //Check if there are to many requests

            } else {
                if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {

                    token = authorizationHeader.substring(7);
                    username = jwtTokenService.extractUsername(token);

                } else {
                    throw new JwtException("Token invalid!");
                }

                if(token != null && username != null) {
                    if(!jwtTokenService.isTokenExpired(token)) {
                        Claims claims = jwtTokenService.extractAllClaims(token);
                        List<SimpleGrantedAuthority> roles = new ArrayList<>();

                        List<String> authorities = claims.get("authorities", ArrayList.class);

                        if(authorities != null && !authorities.isEmpty()) {

                            roles = authorities.stream().map(authority -> new SimpleGrantedAuthority(authority)).collect(Collectors.toList());

                            if(!roles.isEmpty()) {

                                SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(username, null, roles));

                            }
                        }
                    }
                }
            }

            filterChain.doFilter(request, response);

        } catch (MalformedJwtException malformedJwtException) {

            ApiException apiException = new ApiException("Token malformed!", BAD_REQUEST);
            response.setStatus(BAD_REQUEST.value());
            response.setContentType("application/json");
            response.getWriter().write(new ObjectMapper().writeValueAsString(apiException));

        } catch (SignatureException signatureException) {

            ApiException apiException = new ApiException("Token invalid!", BAD_REQUEST);
            response.setStatus(BAD_REQUEST.value());
            response.setContentType("application/json");
            response.getWriter().write(new ObjectMapper().writeValueAsString(apiException));

        } catch (ExpiredJwtException expiredJwtException) {

            ApiException apiException = new ApiException("Token expired!", BAD_REQUEST);
            response.setStatus(BAD_REQUEST.value());
            response.setContentType("application/json");
            response.getWriter().write(new ObjectMapper().writeValueAsString(apiException));

        } catch(NullPointerException nullPointerException) {

            ApiException apiException = new ApiException("Permission denied!", BAD_REQUEST);
            response.setStatus(BAD_REQUEST.value());
            response.setContentType("application/json");
            response.getWriter().write(new ObjectMapper().writeValueAsString(apiException));

        } catch (Exception exception) {

            System.out.println(exception.getClass());

            ApiException apiException = new ApiException(exception.getMessage(), BAD_REQUEST);
            response.setStatus(BAD_REQUEST.value());
            response.setContentType("application/json");
            response.getWriter().write(new ObjectMapper().writeValueAsString(apiException));
        }

    }
}
