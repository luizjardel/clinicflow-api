
package br.com.clinicflow.api.patient.controller;

import br.com.clinicflow.api.patient.dto.PatientRegistrationDTO;
import br.com.clinicflow.api.patient.dto.PatientResponseDTO;
import br.com.clinicflow.api.patient.dto.PatientUpdateDTO;
import br.com.clinicflow.api.patient.service.PatientService;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/pacientes")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping
    public ResponseEntity<Void> cadastrarPaciente(
            @RequestBody @Valid PatientRegistrationDTO patientRegistrationDTO
    ) {
        patientService.cadastrarPaciente(patientRegistrationDTO);

        return ResponseEntity.status(201).build();
    }

    @GetMapping
    public ResponseEntity<List<PatientResponseDTO>> listarPacientes() {
        return ResponseEntity.ok(
                patientService.listarPacientes()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientResponseDTO> buscarPacientePorId(
            @PathVariable UUID id
    ) {
        return ResponseEntity.ok(
                patientService.buscarPacientePorId(id)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientResponseDTO> atualizarPaciente(
            @PathVariable UUID id,
            @RequestBody @Valid PatientUpdateDTO patientUpdateDTO
    ) {
        return ResponseEntity.ok(
                patientService.atualizarPaciente(id, patientUpdateDTO)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> desativarPaciente(
            @PathVariable UUID id
    ) {
        patientService.desativarPaciente(id);
        return ResponseEntity.noContent().build();
    }
    @PatchMapping("/{id}/reativar")
    public ResponseEntity<Void> reativarPaciente(
            @PathVariable UUID id
    ) {
        patientService.reativarPaciente(id);
        return ResponseEntity.noContent().build();
    }
}
