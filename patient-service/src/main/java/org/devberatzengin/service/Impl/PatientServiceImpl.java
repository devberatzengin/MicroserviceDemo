package org.devberatzengin.service.Impl;

import lombok.RequiredArgsConstructor;
import org.devberatzengin.dto.PatientDto;
import org.devberatzengin.model.Patient;
import org.devberatzengin.repository.PatientRepository;
import org.devberatzengin.service.PatientService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor // 🔥 final olan tüm bağımlılıkları constructor ile enjekte eder, @Autowired bitti!
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository; // 🔥 final yaptık, güvenli hale geldi

    @Override
    public PatientDto savePatient(PatientDto patientDto) {
        Patient patient = Patient.builder()
                .firstName(patientDto.getFirstName())
                .lastName(patientDto.getLastName())
                .identificationNumber(patientDto.getIdentificationNumber())
                .email(patientDto.getEmail())
                .bloodType(patientDto.getBloodType())
                .allergies(patientDto.getAllergies())
                .medicalHistory(patientDto.getMedicalHistory())
                .build();

        Patient savedPatient = patientRepository.save(patient);
        return convertToDto(savedPatient);
    }

    @Override
    public List<PatientDto> getAllPatients() {
        return patientRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public PatientDto getPatientById(Long id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hasta bulunamadı! ID: " + id));
        return convertToDto(patient);
    }

    private PatientDto convertToDto(Patient patient) {
        return PatientDto.builder()
                .id(patient.getId())
                .firstName(patient.getFirstName())
                .lastName(patient.getLastName())
                .identificationNumber(patient.getIdentificationNumber())
                .email(patient.getEmail())
                .bloodType(patient.getBloodType())
                .allergies(patient.getAllergies())
                .medicalHistory(patient.getMedicalHistory())
                .build();
    }
}