package com.vetCare.VetCare.application.mapper;

import com.vetCare.VetCare.application.dto.response.CartItemResponseDto;
import com.vetCare.VetCare.application.dto.response.CartResponseDto;
import com.vetCare.VetCare.domain.model.Cart;
import com.vetCare.VetCare.domain.model.CartItem;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CartMapper {

    public CartResponseDto toDto(Cart cart) {
        CartResponseDto dto = new CartResponseDto();
        dto.setId(cart.getId());
        if (cart.getUser() != null) {
            dto.setUserId(cart.getUser().getId());
        }

        List<CartItemResponseDto> itemDtos = cart.getItems().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
        dto.setItems(itemDtos);

        BigDecimal total = itemDtos.stream()
                .map(CartItemResponseDto::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        dto.setTotal(total);

        return dto;
    }

    public CartItemResponseDto toDto(CartItem cartItem) {
        CartItemResponseDto dto = new CartItemResponseDto();
        dto.setId(cartItem.getId());
        if (cartItem.getProduct() != null) {
            dto.setProductId(cartItem.getProduct().getId());
            dto.setProductName(cartItem.getProduct().getName());
            dto.setPrice(cartItem.getProduct().getPrice());
            dto.setQuantity(cartItem.getQuantity());
            dto.setSubtotal(dto.getPrice().multiply(BigDecimal.valueOf(dto.getQuantity())));
        }
        return dto;
    }
}
