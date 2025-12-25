package com.vetCare.VetCare.application.service.impl;

import com.vetCare.VetCare.application.dto.request.UserRequestDto;
import com.vetCare.VetCare.application.dto.response.UserResponseDto;
import com.vetCare.VetCare.application.mapper.UserMapper;
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
    private final UserMapper userMapper;

    @Override
    public UserResponseDto register(UserRequestDto dto) {
        User user = userMapper.toEntity(dto);
        User saved = userRepository.save(user);
        return userMapper.toDto(saved);
    }

    @Override
    public UserResponseDto findById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return userMapper.toDto(user);
    }

    @Override
    public UserResponseDto findByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Email no existe"));
        return userMapper.toDto(user);
    }

    @Override
    public List<UserResponseDto> findAll() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    @Override
    public UserResponseDto update(Long id, UserRequestDto dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        user.setName(dto.getName());
        user.setPhone(dto.getPhone());
        // No actualizamos email ni password aquí por seguridad

        User updated = userRepository.save(user);
        return userMapper.toDto(updated);
    }

    @Override
    public void deactivate(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        user.setIsActive(false);
        userRepository.save(user);
    }
}
