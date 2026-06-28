package org.devberatzengin.service.Impl;

import org.devberatzengin.dto.AppointmentDto;
import org.devberatzengin.exception.ResourceNotFoundException;
import org.devberatzengin.model.Appointment;
import org.devberatzengin.model.AppointmentStatus;
import org.devberatzengin.repository.AppointmentRepository;
import org.devberatzengin.service.AppointmentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;

    // 🚨 KURUMSAL STANDART: Constructor Injection
    public AppointmentServiceImpl(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public AppointmentDto createAppointment(AppointmentDto appointmentDto) {
        Appointment appointment = Appointment.builder()
                .patientId(appointmentDto.getPatientId())
                .doctorId(appointmentDto.getDoctorId())
                .appointmentDate(appointmentDto.getAppointmentDate())
                .status(AppointmentStatus.SCHEDULED) // 📅 Yeni randevu otomatik SCHEDULED başlar
                .notes(appointmentDto.getNotes())
                .build();

        Appointment savedAppointment = appointmentRepository.save(appointment);
        return mapToDto(savedAppointment);
    }

    @Override
    public List<AppointmentDto> getAllAppointments() {
        return appointmentRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public AppointmentDto getAppointmentById(Long id) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not found with id: " + id));
        return mapToDto(appointment);
    }

    @Override
    public List<AppointmentDto> getAppointmentsByPatientId(Long patientId) {
        return appointmentRepository.findByPatientId(patientId).stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<AppointmentDto> getAppointmentsByDoctorId(Long doctorId) {
        return appointmentRepository.findByDoctorId(doctorId).stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public AppointmentDto cancelAppointment(Long id) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found with id: " + id));

        appointment.setStatus(AppointmentStatus.CANCELLED); // ❌ İptal mührü
        Appointment updatedAppointment = appointmentRepository.save(appointment);
        return mapToDto(updatedAppointment);
    }

    // 🔄 MAPPING: Entity -> DTO Dönüşüm Motoru
    private AppointmentDto mapToDto(Appointment appointment) {
        return AppointmentDto.builder()
                .id(appointment.getId())
                .patientId(appointment.getPatientId())
                .doctorId(appointment.getDoctorId())
                .appointmentDate(appointment.getAppointmentDate())
                .status(appointment.getStatus())
                .notes(appointment.getNotes())
                .build();
    }
}