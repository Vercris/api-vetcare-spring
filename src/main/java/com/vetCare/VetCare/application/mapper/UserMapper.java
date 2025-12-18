package com.vetCare.VetCare.application.mapper;

import com.vetCare.VetCare.application.dto.request.UserRequestDto;
import com.vetCare.VetCare.application.dto.response.UserResponseDto;
import com.vetCare.VetCare.domain.model.User;
import com.vetCare.VetCare.domain.model.enums.UserRole;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toEntity(UserRequestDto dto) {
        User user = new User();
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword()); // luego se encripta
        user.setName(dto.getName());
        user.setPhone(dto.getPhone());
        user.setRole(UserRole.CUSTOMER);
        user.setIsActive(true);
        return user;
    }

    public UserResponseDto toDto(User user) {
        UserResponseDto dto = new UserResponseDto();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setName(user.getName());
        dto.setPhone(user.getPhone());
        dto.setRole(user.getRole());
        dto.setIsActive(user.getIsActive());
        return dto;
    }
}

