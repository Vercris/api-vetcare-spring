package com.vetCare.VetCare.application.service.impl;

import com.vetCare.VetCare.application.dto.request.OrderItemRequestDto;
import com.vetCare.VetCare.application.dto.request.OrderRequestDto;
import com.vetCare.VetCare.application.dto.response.OrderResponseDto;
import com.vetCare.VetCare.application.mapper.OrderMapper;
import com.vetCare.VetCare.application.service.OrderService;
import com.vetCare.VetCare.domain.model.Order;
import com.vetCare.VetCare.domain.model.OrderItem;
import com.vetCare.VetCare.domain.model.Product;
import com.vetCare.VetCare.domain.model.User;
import com.vetCare.VetCare.domain.model.enums.OrderStatus;
import com.vetCare.VetCare.domain.repository.OrderRepository;
import com.vetCare.VetCare.domain.repository.ProductRepository;
import com.vetCare.VetCare.domain.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final OrderMapper orderMapper;

    @Override
    @Transactional
    public OrderResponseDto createOrder(OrderRequestDto dto, User user) {

        Order order = new Order();
        order.setUser(user);
        order.setAddress(dto.getAddress());
        order.setStatus(OrderStatus.PENDING);

        BigDecimal subtotal = BigDecimal.ZERO;

        List<OrderItem> items = new ArrayList<>();

        for (OrderItemRequestDto itemDto : dto.getItems()) {

            Product product = productRepository.findById(itemDto.getProductId())
                    .orElseThrow(() ->
                            new RuntimeException("Producto no encontrado con ID: " + itemDto.getProductId())
                    );

            if (product.getStockQuantity() < itemDto.getQuantity()) {
                throw new RuntimeException(
                        "Stock insuficiente para el producto: " + product.getName()
                );
            }

            BigDecimal itemSubtotal =
                    product.getPrice().multiply(BigDecimal.valueOf(itemDto.getQuantity()));

            OrderItem item = new OrderItem();
            item.setProduct(product);
            item.setQuantity(itemDto.getQuantity());
            item.setUnitPrice(product.getPrice());
            item.setSubtotal(itemSubtotal);
            item.setOrder(order);

            items.add(item);
            subtotal = subtotal.add(itemSubtotal);

            // 🔥 Descontar stock
            product.setStockQuantity(product.getStockQuantity() - itemDto.getQuantity());
        }

        BigDecimal tax = subtotal.multiply(BigDecimal.valueOf(0.18));
        BigDecimal total = subtotal.add(tax);

        order.setItems(items);
        order.setSubtotal(subtotal);
        order.setTax(tax);
        order.setShippingCost(BigDecimal.ZERO);
        order.setTotal(total);

        return orderMapper.toDto(orderRepository.save(order));
    }

    @Override
    @Transactional
    public List<OrderResponseDto> getOrdersByUser(User user) {

        return orderRepository.findByUser_Id(user)
                .stream()
                .map(orderMapper::toDto)
                .toList();
    }

    @Override
    @Transactional
    public OrderResponseDto findById(Long id) {

        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Orden no encontrada"));

        return orderMapper.toDto(order);
    }


}
