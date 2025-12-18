package com.vetCare.VetCare.application.service.impl;

import com.vetCare.VetCare.application.service.UserService;
import com.vetCare.VetCare.domain.model.User;
import com.vetCare.VetCare.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public User register(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Email no existe"));
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    //Desactivar
    @Override
    public void deactivate(Long id) {
        User user = findById(id);
        user.setIsActive(false);
        userRepository.save(user);
    }
}
