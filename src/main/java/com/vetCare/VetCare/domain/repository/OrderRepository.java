package com.vetCare.VetCare.domain.repository;

import com.vetCare.VetCare.domain.model.Order;
import com.vetCare.VetCare.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {
    List<Order> findByUser_Id(User userId);

    //Historial De Pedidos
}
