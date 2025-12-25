package com.vetCare.VetCare.application.service;

import com.vetCare.VetCare.application.dto.request.CartItemRequestDto;
import com.vetCare.VetCare.application.dto.response.CartResponseDto;

public interface CartService {
    CartResponseDto getCartByUserId(Long userId);
    CartResponseDto addItemToCart(Long userId, CartItemRequestDto itemDto);
    CartResponseDto removeItemFromCart(Long userId, Long cartItemId);
    CartResponseDto updateItemQuantity(Long userId, Long cartItemId, Integer quantity);
    void clearCart(Long userId);
}
