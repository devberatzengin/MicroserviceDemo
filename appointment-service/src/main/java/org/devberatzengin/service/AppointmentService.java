package org.devberatzengin.service;

import org.devberatzengin.dto.AppointmentDto;
import java.util.List;

public interface AppointmentService {
    AppointmentDto createAppointment(AppointmentDto appointmentDto);
    List<AppointmentDto> getAllAppointments();
    AppointmentDto getAppointmentById(Long id);
    List<AppointmentDto> getAppointmentsByPatientId(Long patientId);
    List<AppointmentDto> getAppointmentsByDoctorId(Long doctorId);
    AppointmentDto cancelAppointment(Long id);
}