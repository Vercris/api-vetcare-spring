package com.vetCare.VetCare.application.dto.response;

import lombok.Data;
import java.util.Set;

@Data
public class UserResponseDto {
    private Long id;
    private String email;
    private String name;
    private String phone;
    private Set<String> roles;
    private Boolean isActive;
}
