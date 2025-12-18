package com.vetCare.VetCare.application.dto.request;

import lombok.Data;

@Data
public class UserRequestDto {
    private String email;
    private String password;
    private String name;
    private String phone;
}
