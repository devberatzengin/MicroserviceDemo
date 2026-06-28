package org.devberatzengin.controller;

import jakarta.validation.Valid;
import org.devberatzengin.dto.AppointmentDto;
import org.devberatzengin.service.AppointmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;

    // 🚨 Constructor Injection
    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> createAppointment(@Valid @RequestBody AppointmentDto appointmentDto) {
        AppointmentDto created = appointmentService.createAppointment(appointmentDto);

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("success", true);
        response.put("message", "Appointment created successfully");
        response.put("data", created);
        response.put("timestamp", LocalDateTime.now());

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllAppointments() {
        List<AppointmentDto> appointments = appointmentService.getAllAppointments();

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("success", true);
        response.put("message", "All appointments retrieved successfully");
        response.put("data", appointments);
        response.put("timestamp", LocalDateTime.now());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getAppointmentById(@PathVariable Long id) {
        AppointmentDto appointment = appointmentService.getAppointmentById(id);

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("success", true);
        response.put("message", "Appointment retrieved successfully with id: " + id);
        response.put("data", appointment);
        response.put("timestamp", LocalDateTime.now());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<Map<String, Object>> getAppointmentsByPatientId(@PathVariable Long patientId) {
        List<AppointmentDto> appointments = appointmentService.getAppointmentsByPatientId(patientId);

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("success", true);
        response.put("message", "Patient's appointments retrieved successfully");
        response.put("data", appointments);
        response.put("timestamp", LocalDateTime.now());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<Map<String, Object>> getAppointmentsByDoctorId(@PathVariable Long doctorId) {
        List<AppointmentDto> appointments = appointmentService.getAppointmentsByDoctorId(doctorId);

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("success", true);
        response.put("message", "Doctor's schedule retrieved successfully");
        response.put("data", appointments);
        response.put("timestamp", LocalDateTime.now());

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}/cancel")
    public ResponseEntity<Map<String, Object>> cancelAppointment(@PathVariable Long id) {
        AppointmentDto cancelled = appointmentService.cancelAppointment(id);

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("success", true);
        response.put("message", "Appointment cancelled successfully");
        response.put("data", cancelled);
        response.put("timestamp", LocalDateTime.now());

        return ResponseEntity.ok(response);
    }
}