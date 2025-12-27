package com.vetCare.VetCare.application.service.impl;

import com.vetCare.VetCare.application.service.RefreshTokenService;
import com.vetCare.VetCare.domain.model.RefreshToken;
import com.vetCare.VetCare.domain.model.User;
import com.vetCare.VetCare.domain.repository.RefreshTokenRepository;
import com.vetCare.VetCare.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenServiceImpl implements RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository;

    @Value("${app.jwt.refresh-token-duration-ms:604800000}")
    private Long refreshTokenDurationMs;

    @Override
    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    @Override
    @Transactional
    public RefreshToken createRefreshToken(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Busca si ya existe un token para este usuario
        return refreshTokenRepository.findByUser(user)
                .map(token -> {
                    // Si existe, actualízalo
                    token.setExpiryDate(Instant.now().plusMillis(refreshTokenDurationMs));
                    token.setToken(UUID.randomUUID().toString());
                    return refreshTokenRepository.save(token);
                })
                .orElseGet(() -> {
                    // Si no existe, crea uno nuevo
                    RefreshToken newRefreshToken = RefreshToken.builder()
                            .user(user)
                            .expiryDate(Instant.now().plusMillis(refreshTokenDurationMs))
                            .token(UUID.randomUUID().toString())
                            .build();
                    return refreshTokenRepository.save(newRefreshToken);
                });
    }

    @Override
    public RefreshToken verifyExpiration(RefreshToken token) {
        if (token.getExpiryDate().isBefore(Instant.now())) {
            refreshTokenRepository.delete(token);
            throw new RuntimeException("El token de refresco ha expirado. Por favor, inicie sesión de nuevo.");
        }
        return token;
    }
}
