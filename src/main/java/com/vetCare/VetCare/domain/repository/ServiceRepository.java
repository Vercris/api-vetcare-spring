package com.vetCare.VetCare.domain.repository;

import com.vetCare.VetCare.domain.model.ServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<ServiceEntity,Long> {
}
