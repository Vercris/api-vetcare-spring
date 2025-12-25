package com.vetCare.VetCare.application.service;

import com.vetCare.VetCare.application.dto.request.UserRequestDto;
import com.vetCare.VetCare.application.dto.response.UserResponseDto;

import java.util.List;

public interface UserService {

    UserResponseDto register(UserRequestDto dto);

    UserResponseDto findById(Long id);

    UserResponseDto findByEmail(String email);

    List<UserResponseDto> findAll();

    UserResponseDto update(Long id, UserRequestDto dto);

    void deactivate(Long id);
}
