package com.vetCare.VetCare.domain.repository;

import com.vetCare.VetCare.domain.model.UserSecurity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserSecurityRepository extends JpaRepository<UserSecurity, Long> {
    Optional<UserSecurity> findByUsername(String username);
}
