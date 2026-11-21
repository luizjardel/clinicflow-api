package br.com.clinicflow.api.patient.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import java.time.LocalDate;

public record PatientUpdateDTO(

        @NotBlank(message = "O nome completo é obrigatório.")
        String fullName,

        @NotBlank(message = "O telefone é obrigatório.")
        String phone,

        @NotNull(message = "A data de nascimento é obrigatória.")
        @Past(message = "A data de nascimento deve estar no passado.")
        LocalDate birthDate

) {
}