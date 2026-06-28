package org.devberatzengin.dto;

import lombok.*;
import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorResponse {
    private int status;
    private String message;
    private LocalDateTime timestamp;
    private Map<String, String> validationErrors; // Validation patlarsa hangi alan niye patladı buraya yazacağız kanka
}