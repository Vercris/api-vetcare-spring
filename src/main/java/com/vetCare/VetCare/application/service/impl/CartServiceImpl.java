package com.vetCare.VetCare.application.service.impl;

import com.vetCare.VetCare.application.dto.request.CartItemRequestDto;
import com.vetCare.VetCare.application.dto.response.CartResponseDto;
import com.vetCare.VetCare.application.mapper.CartMapper;
import com.vetCare.VetCare.application.service.CartService;
import com.vetCare.VetCare.domain.model.*;
import com.vetCare.VetCare.domain.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final CartMapper cartMapper;

    @Override
    @Transactional
    public CartResponseDto getCartByUserId(Long userId) {
        Cart cart = findOrCreateCartByUserId(userId);
        return cartMapper.toDto(cart);
    }

    @Override
    @Transactional
    public CartResponseDto addItemToCart(Long userId, CartItemRequestDto itemDto) {
        Cart cart = findOrCreateCartByUserId(userId);
        Product product = productRepository.findById(itemDto.getProductId())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        Optional<CartItem> existingItem = cart.getItems().stream()
                .filter(item -> item.getProduct().getId().equals(itemDto.getProductId()))
                .findFirst();

        if (existingItem.isPresent()) {
            CartItem item = existingItem.get();
            item.setQuantity(item.getQuantity() + itemDto.getQuantity());
            cartItemRepository.save(item);
        } else {
            CartItem newItem = new CartItem();
            newItem.setCart(cart);
            newItem.setProduct(product);
            newItem.setQuantity(itemDto.getQuantity());
            cart.getItems().add(newItem);
            cartItemRepository.save(newItem);
        }

        cart.setUpdatedAt(LocalDateTime.now());
        Cart updatedCart = cartRepository.save(cart);
        return cartMapper.toDto(updatedCart);
    }

    @Override
    @Transactional
    public CartResponseDto removeItemFromCart(Long userId, Long cartItemId) {
        Cart cart = findOrCreateCartByUserId(userId);
        CartItem itemToRemove = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new RuntimeException("Ítem del carrito no encontrado"));

        if (!itemToRemove.getCart().getId().equals(cart.getId())) {
            throw new RuntimeException("Este ítem no pertenece al carrito del usuario");
        }

        cart.getItems().remove(itemToRemove);
        cartItemRepository.delete(itemToRemove);

        cart.setUpdatedAt(LocalDateTime.now());
        Cart updatedCart = cartRepository.save(cart);
        return cartMapper.toDto(updatedCart);
    }

    @Override
    @Transactional
    public CartResponseDto updateItemQuantity(Long userId, Long cartItemId, Integer quantity) {
        if (quantity <= 0) {
            return removeItemFromCart(userId, cartItemId);
        }

        Cart cart = findOrCreateCartByUserId(userId);
        CartItem itemToUpdate = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new RuntimeException("Ítem del carrito no encontrado"));

        if (!itemToUpdate.getCart().getId().equals(cart.getId())) {
            throw new RuntimeException("Este ítem no pertenece al carrito del usuario");
        }

        itemToUpdate.setQuantity(quantity);
        cartItemRepository.save(itemToUpdate);

        cart.setUpdatedAt(LocalDateTime.now());
        Cart updatedCart = cartRepository.save(cart);
        return cartMapper.toDto(updatedCart);
    }

    @Override
    @Transactional
    public void clearCart(Long userId) {
        Cart cart = findOrCreateCartByUserId(userId);
        cartItemRepository.deleteAll(cart.getItems());
        cart.getItems().clear();
        cart.setUpdatedAt(LocalDateTime.now());
        cartRepository.save(cart);
    }

    private Cart findOrCreateCartByUserId(Long userId) {
        return cartRepository.findByUser_Id(userId).orElseGet(() -> {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
            Cart newCart = new Cart();
            newCart.setUser(user);
            newCart.setCreatedAt(LocalDateTime.now());
            newCart.setUpdatedAt(LocalDateTime.now());
            newCart.setItems(new ArrayList<>());
            return cartRepository.save(newCart);
        });
    }
}
