package com.vetCare.VetCare.web.controller;

import com.vetCare.VetCare.application.dto.request.CartItemRequestDto;
import com.vetCare.VetCare.application.dto.response.CartResponseDto;
import com.vetCare.VetCare.application.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping("/{userId}")
    public ResponseEntity<CartResponseDto> getCart(@PathVariable Long userId) {
        return ResponseEntity.ok(cartService.getCartByUserId(userId));
    }

    @PostMapping("/{userId}/items")
    public ResponseEntity<CartResponseDto> addItem(
            @PathVariable Long userId,
            @RequestBody CartItemRequestDto itemDto) {
        return ResponseEntity.ok(cartService.addItemToCart(userId, itemDto));
    }

    @DeleteMapping("/{userId}/items/{itemId}")
    public ResponseEntity<CartResponseDto> removeItem(
            @PathVariable Long userId,
            @PathVariable Long itemId) {
        return ResponseEntity.ok(cartService.removeItemFromCart(userId, itemId));
    }

    @PutMapping("/{userId}/items/{itemId}")
    public ResponseEntity<CartResponseDto> updateItemQuantity(
            @PathVariable Long userId,
            @PathVariable Long itemId,
            @RequestParam Integer quantity) {
        return ResponseEntity.ok(cartService.updateItemQuantity(userId, itemId, quantity));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> clearCart(@PathVariable Long userId) {
        cartService.clearCart(userId);
        return ResponseEntity.noContent().build();
    }
}
