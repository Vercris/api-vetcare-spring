package com.vetCare.VetCare.security;

import com.vetCare.VetCare.security.filters.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtAuthenticationFilter filter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity.
        csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        // Endpoints Públicos
                        .requestMatchers("/api/auth/**").permitAll()
                        // Permitir registro de usuarios
                        .requestMatchers(HttpMethod.POST, "/api/users").permitAll()
                        // Permitir ver productos, categorías y servicios
                        .requestMatchers(HttpMethod.GET, "/api/products/**", "/api/categories/**", "/api/services/**").permitAll()
                        // Permitir ver horarios disponibles
                        .requestMatchers(HttpMethod.GET, "/api/schedules/available").permitAll()

                        // Endpoints Protegidos por Rol
                        // ADMIN: puede gestionar (crear, editar, borrar) y ver listados completos.
                        .requestMatchers(HttpMethod.GET, "/api/users", "/api/orders").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/products", "/api/categories", "/api/services", "/api/schedules").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/products/**", "/api/categories/**", "/api/services/**", "/api/schedules/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/users/**", "/api/products/**", "/api/categories/**", "/api/services/**", "/api/schedules/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PATCH, "/api/orders/**/status").hasRole("ADMIN")

                        .anyRequest().authenticated()
                )
                .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception{
        return configuration.getAuthenticationManager();
    }
}
