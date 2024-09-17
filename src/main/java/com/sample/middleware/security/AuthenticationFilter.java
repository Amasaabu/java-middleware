package com.sample.middleware.security;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.shaded.gson.JsonObject;
import com.sample.middleware.models.MerchantDetails;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;

//grab auth details
@AllArgsConstructor
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticationManager;
    private Environment env;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            MerchantDetails user = new ObjectMapper().readValue(request.getInputStream(), MerchantDetails.class);
            Authentication auth = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
            System.out.println(user.getPassword());
            return authenticationManager.authenticate(auth);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        System.out.println(authResult.getName());
        String token = JWT.create().withSubject(authResult.getName())
                .withExpiresAt(new Date(System.currentTimeMillis() + 720000))
                .sign(Algorithm.HMAC512(env.getProperty("HMACSECRETE")));

//        response.addHeader("Authorization", "Bearer " + token);

        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json");
        var jsonObj = new JsonObject();
        jsonObj.addProperty("token", "Bearer " + token);
        var out = response.getWriter();
        out.print(jsonObj);
        out.flush();
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write(failed.getMessage());
        response.getWriter().flush();
    }
}