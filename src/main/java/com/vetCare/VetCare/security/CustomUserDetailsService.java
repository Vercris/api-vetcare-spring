package com.vetCare.VetCare.security;

import com.coudevi.domain.model.UserSecurity;
import com.coudevi.domain.repository.UserSecurityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserSecurityRepository userSecurityRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserSecurity  userSecurity = userSecurityRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario: " + username + " no existe"));
        return User.builder()
                .username(userSecurity.getUsername())
                .password(userSecurity.getPassword())
                .authorities(userSecurity.getRoles()
                        .stream()
                        .map(r -> new SimpleGrantedAuthority("ROLE_"+r.getName()))
                        .collect(Collectors.toSet()))
                .build();

    }
}
