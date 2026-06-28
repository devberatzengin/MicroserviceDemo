package org.devberatzengin.dto;


import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.devberatzengin.model.AppointmentStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppointmentDto {

    private Long id;

    @NotNull(message = "Patient ID cannot be null")
    private Long patientId;

    @NotNull(message = "Doctor ID cannot be null")
    private Long doctorId;

    // 🚨 KURUMSAL VALIDASYON: Randevu tarihi geçmiş bir zaman olamaz, gelecekte olmalı!
    @NotNull(message = "Appointment date cannot be null")
    @Future(message = "Appointment date must be in the future")
    private LocalDateTime appointmentDate;

    private AppointmentStatus status; // default SCHEDULED basacağız service'te

    private String notes;
}
