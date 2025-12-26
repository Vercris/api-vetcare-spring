package com.vetCare.VetCare.domain.model.enums;

public enum AppointmentStatus {
    SCHEDULED,  // La cita está agendada para el futuro.
    COMPLETED,  // La cita se ha completado con éxito.
    CANCELLED,  // La cita fue cancelada (por el usuario o el admin).
    NO_SHOW     // El usuario no se presentó a la cita.
}
