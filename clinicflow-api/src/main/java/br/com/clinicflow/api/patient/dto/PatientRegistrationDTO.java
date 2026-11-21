
package br.com.clinicflow.api.patient.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

public record PatientRegistrationDTO(

        @NotBlank(message = "O nome completo é obrigatório.")
        String fullName,

        @NotBlank(message = "O e-mail é obrigatório.")
        @Email(message = "Informe um e-mail válido.")
        String email,

        @NotBlank(message = "O telefone é obrigatório.")
        String phone,

        @NotNull(message = "A data de nascimento é obrigatória.")
        @Past(message = "A data de nascimento deve estar no passado.")
        LocalDate birthDate,

        @NotBlank(message = "A senha é obrigatória.")
        @Size(min = 8, message = "A senha deve possuir pelo menos 8 caracteres.")
        @Pattern(
                regexp = "^(?=.*[a-z])(?=.*\\d).*$",
                message = "A senha deve possuir pelo menos uma letra minúscula e um número."
        )
        String password

) {
}
