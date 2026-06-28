package org.devberatzengin.service;

import org.devberatzengin.dto.PatientDto;
import java.util.List;

public interface PatientService {
    PatientDto savePatient(PatientDto patientDto);
    List<PatientDto> getAllPatients();
    PatientDto getPatientById(Long id);
}