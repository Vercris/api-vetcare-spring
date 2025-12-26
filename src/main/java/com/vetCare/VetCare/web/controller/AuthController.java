package com.vetCare.VetCare.web.controller;

import com.vetCare.VetCare.application.dto.request.LoginRequest;
import com.vetCare.VetCare.application.dto.request.RefreshTokenRequest;
import com.vetCare.VetCare.application.dto.response.LoginResponse;
import com.vetCare.VetCare.application.dto.response.RefreshTokenResponse;
import com.vetCare.VetCare.security.CustomUserDetailsService;
import com.vetCare.VetCare.security.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService customUserDetailsService;
    private final JwtUtil jwtUtil;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );

        final UserDetails userDetails = customUserDetailsService.loadUserByUsername(loginRequest.getUsername());
        final String accessToken = jwtUtil.generateAccessToken(userDetails);
        final String refreshToken = jwtUtil.generateRefreshToken(userDetails);

        return new LoginResponse(accessToken, refreshToken);
    }

    @PostMapping("/refresh")
    public RefreshTokenResponse refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        String refreshToken = refreshTokenRequest.getRefreshToken();
        String username = jwtUtil.extractUsername(refreshToken);
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);

        if (jwtUtil.validateToken(refreshToken, userDetails)) {
            String newAccessToken = jwtUtil.generateAccessToken(userDetails);
            return new RefreshTokenResponse(newAccessToken);
        } else {
            // Aquí podrías lanzar una excepción de token inválido o expirado
            throw new RuntimeException("Invalid or expired refresh token");
        }
    }
}
