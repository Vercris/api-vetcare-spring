package com.vetCare.VetCare.domain.repository;

import com.vetCare.VetCare.domain.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {

    List<Product> findByIsActiveTrue();
    List<Product> findByNameContainingIgnoreCase(String name);

    //Permite Listar productos activos
    //Busqueda por nombre
}
