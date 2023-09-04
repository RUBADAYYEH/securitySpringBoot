package com.security.service;

import com.security.dao.Request.SighnUpRequest;
import com.security.dao.Request.SighninRequest;

import com.security.dao.Response.JwtAuthenticationResponse;

public interface AuthService {
    JwtAuthenticationResponse signup(SighnUpRequest request);
    JwtAuthenticationResponse signin(SighninRequest request);
}
