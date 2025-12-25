package com.vetCare.VetCare.web.controller;

import com.vetCare.VetCare.application.dto.request.UserRequestDto;
import com.vetCare.VetCare.application.dto.response.UserResponseDto;
import com.vetCare.VetCare.application.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDto> register(
            @RequestBody UserRequestDto dto) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userService.register(dto));
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> list() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> findById(
            @PathVariable Long id) {

        return ResponseEntity.ok(userService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto> update(
            @PathVariable Long id,
            @RequestBody UserRequestDto dto) {
        return ResponseEntity.ok(userService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deactivate(@PathVariable Long id) {
        userService.deactivate(id);
        return ResponseEntity.noContent().build();
    }
}
