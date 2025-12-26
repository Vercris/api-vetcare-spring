package com.vetCare.VetCare.domain.repository;

import com.vetCare.VetCare.domain.model.RefreshToken;
import com.vetCare.VetCare.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByToken(String token);
    Optional<RefreshToken> findByUser(User user); // Añadido para buscar por usuario
    int deleteByUser(User user);
}
