package org.devberatzengin.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.devberatzengin.dto.PatientDto;
import org.devberatzengin.service.PatientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/patients")
@RequiredArgsConstructor // 🔥 Constructor Injection
public class PatientController {

    private final PatientService patientService;

    @PostMapping
    // 🚨 @Valid ile gelen DTO'yu kapıda denetliyoruz, HttpStatus.CREATED (201) dönüyoruz kanka
    public ResponseEntity<PatientDto> createPatient(@Valid @RequestBody PatientDto patientDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(patientService.savePatient(patientDto));
    }

    @GetMapping
    public ResponseEntity<List<PatientDto>> getAllPatients() {
        return ResponseEntity.ok(patientService.getAllPatients());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientDto> getPatientById(@PathVariable Long id) {
        return ResponseEntity.ok(patientService.getPatientById(id));
    }
}