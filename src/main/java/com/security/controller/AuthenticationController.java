package com.security.controller;

import com.security.dao.Request.SighnUpRequest;
import com.security.dao.Request.SighninRequest;
import com.security.dao.Response.JwtAuthenticationResponse;
import com.security.service.AuthServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.security.service.AuthService;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthServiceImpl authService;
    @PostMapping("/signup")
    public ResponseEntity<JwtAuthenticationResponse> signUp(@RequestBody SighnUpRequest request){
        return ResponseEntity.ok(authService.signup(request));
   }
    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody SighninRequest request){
        return ResponseEntity.ok(authService.signin(request));
    }
}
