
package br.com.clinicflow.api.patient.dto;

import java.time.LocalDate;
import java.util.UUID;

public record PatientResponseDTO(
        UUID id,
        String fullName,
        String phone,
        LocalDate birthDate
) {
}
