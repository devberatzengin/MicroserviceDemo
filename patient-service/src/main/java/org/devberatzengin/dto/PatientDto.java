package org.devberatzengin.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PatientDto {

    private Long id;

    @NotBlank(message = "Hasta adı boş bırakılamaz!")
    @Size(min = 2, message = "Hasta adı en az 2 karakter olmalıdır!")
    private String firstName;

    @NotBlank(message = "Hasta soyadı boş bırakılamaz!")
    private String lastName;

    @NotBlank(message = "TC Kimlik numarası boş bırakılamaz!")
    @Size(min = 11, max = 11, message = "TC Kimlik numarası tam 11 haneli olmalıdır!")
    private String identificationNumber;

    @Email(message = "Geçersiz e-mail formatı!")
    private String email;

    private String bloodType;
    private String allergies;
    private String medicalHistory;
}