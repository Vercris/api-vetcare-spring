package com.vetCare.VetCare.domain.repository;

import com.vetCare.VetCare.domain.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}
