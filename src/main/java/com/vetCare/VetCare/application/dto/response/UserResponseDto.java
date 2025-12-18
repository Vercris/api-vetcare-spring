package com.vetCare.VetCare.application.dto.response;

import com.vetCare.VetCare.domain.model.enums.UserRole;
import lombok.Data;

@Data
public class UserResponseDto {
    private Long id;
    private String email;
    private String name;
    private String phone;
    private UserRole role;
    private Boolean isActive;
}
