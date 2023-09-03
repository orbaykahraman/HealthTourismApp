package com.example.healthtourismapplication.controller;

import com.example.healthtourismapplication.database.entity.UserInfo;
import com.example.healthtourismapplication.model.requestDTO.AuthRequest;
import com.example.healthtourismapplication.config.JWTService;
import com.example.healthtourismapplication.service.UserInfoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Tag(name = "User Info")
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private AuthenticationManager authManager;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody @Valid UserInfo userInfo) {
        return userInfoService.register(userInfo);
    }

    @PostMapping("/authenticate")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(),authRequest.getPassword()));

        if(authentication.isAuthenticated()) {
            return jwtService.generateToken(authRequest.getUsername());
        }

        else {
            throw new UsernameNotFoundException("Invalid user request!");
        }
    }


}