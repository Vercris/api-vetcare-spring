package com.vetCare.VetCare.domain.repository;

import com.vetCare.VetCare.application.dto.response.TopServiceReportDto;
import com.vetCare.VetCare.domain.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByUserId(Long userId);
    List<Appointment> findByServiceId(Long serviceId);

    @Query("SELECT new com.vetCare.VetCare.application.dto.response.TopServiceReportDto(a.service.id, a.service.name, COUNT(a)) " +
           "FROM Appointment a " +
           "GROUP BY a.service.id, a.service.name " +
           "ORDER BY COUNT(a) DESC")
    List<TopServiceReportDto> findTopServices();
}
