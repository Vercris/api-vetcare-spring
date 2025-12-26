package com.vetCare.VetCare.web.controller;

import com.vetCare.VetCare.application.dto.request.LoginRequestDto;
import com.vetCare.VetCare.application.dto.request.RefreshTokenRequestDto;
import com.vetCare.VetCare.application.dto.response.JwtResponseDto;
import com.vetCare.VetCare.application.service.RefreshTokenService;
import com.vetCare.VetCare.config.jwt.JwtUtil;
import com.vetCare.VetCare.domain.model.RefreshToken;
import com.vetCare.VetCare.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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
    private final JwtUtil jwtUtil;
    private final RefreshTokenService refreshTokenService;

    @PostMapping("/login")
    public ResponseEntity<JwtResponseDto> login(@RequestBody LoginRequestDto request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = (User) userDetails;

        String accessToken = jwtUtil.generateToken(userDetails);
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(user.getId());

        return ResponseEntity.ok(new JwtResponseDto(accessToken, refreshToken.getToken()));
    }

    @PostMapping("/refresh")
    public ResponseEntity<JwtResponseDto> refreshToken(@RequestBody RefreshTokenRequestDto request) {
        return refreshTokenService.findByToken(request.getToken())
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(user -> {
                    String accessToken = jwtUtil.generateToken(user);
                    return ResponseEntity.ok(new JwtResponseDto(accessToken, request.getToken()));
                })
                .orElseThrow(() -> new RuntimeException("Token de refresco no encontrado en la base de datos."));
    }
}
