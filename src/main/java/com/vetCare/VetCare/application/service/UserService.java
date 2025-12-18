package com.vetCare.VetCare.application.service;

import com.vetCare.VetCare.domain.model.User;

import java.util.List;

public interface UserService {
    User register(User user);

    User findById(Long id);

    User findByEmail(String email);

    List<User> findAll();

    void deactivate(Long id);
}
