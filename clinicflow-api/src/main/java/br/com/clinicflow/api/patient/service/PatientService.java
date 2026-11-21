
package br.com.clinicflow.api.patient.service;

import br.com.clinicflow.api.patient.domain.Patient;
import br.com.clinicflow.api.patient.dto.PatientRegistrationDTO;
import br.com.clinicflow.api.patient.dto.PatientResponseDTO;
import br.com.clinicflow.api.patient.dto.PatientUpdateDTO;
import br.com.clinicflow.api.patient.repository.PatientRepository;
import br.com.clinicflow.api.shared.exception.EmailAlreadyRegisteredException;
import br.com.clinicflow.api.shared.exception.PatientNotFoundException;
import br.com.clinicflow.api.shared.exception.PhoneAlreadyRegisteredException;
import br.com.clinicflow.api.user.domain.Role;
import br.com.clinicflow.api.user.domain.User;
import br.com.clinicflow.api.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PatientService {

    private final PatientRepository patientRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public PatientService(
            PatientRepository patientRepository,
            UserRepository userRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.patientRepository = patientRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void verificarTelefone(String phone) {
        if (patientRepository.existsByPhone(phone)) {
            throw new PhoneAlreadyRegisteredException("Telefone já cadastrado!");
        }
    }

    public void verificarEmail(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new EmailAlreadyRegisteredException("E-mail já cadastrado!");
        }
    }

    @Transactional
    public void cadastrarPaciente(PatientRegistrationDTO patientRegistrationDTO) {
        verificarEmail(patientRegistrationDTO.email());
        verificarTelefone(patientRegistrationDTO.phone());

        User user = new User(
                patientRegistrationDTO.email(),
                passwordEncoder.encode(patientRegistrationDTO.password()),
                Role.PATIENT
        );

        User savedUser = userRepository.save(user);

        Patient patient = new Patient(
                patientRegistrationDTO.fullName(),
                patientRegistrationDTO.phone(),
                patientRegistrationDTO.birthDate(),
                savedUser
        );

        patientRepository.save(patient);
    }

    public List<PatientResponseDTO> listarPacientes() {
        return patientRepository.findAllByActiveTrue()
                .stream()
                .map(patient -> new PatientResponseDTO(
                        patient.getId(),
                        patient.getFullName(),
                        patient.getPhone(),
                        patient.getBirthDate()
                ))
                .toList();
    }

    public PatientResponseDTO buscarPacientePorId(UUID id) {
        Patient patient = patientRepository.findByIdAndActiveTrue(id)
                .orElseThrow(() -> new PatientNotFoundException(
                        "Paciente não encontrado!"
                ));

        return new PatientResponseDTO(
                patient.getId(),
                patient.getFullName(),
                patient.getPhone(),
                patient.getBirthDate()
        );
    }

    @Transactional
    public PatientResponseDTO atualizarPaciente(UUID id, PatientUpdateDTO dto) {

        Patient patient = patientRepository.findByIdAndActiveTrue(id)
                .orElseThrow(() -> new PatientNotFoundException(
                        "Paciente não encontrado!"
                ));

        if (patientRepository.existsByPhoneAndIdNot(dto.phone(), id)) {
            throw new PhoneAlreadyRegisteredException("Telefone já cadastrado!");
        }

        patient.atualizarDados(
                dto.fullName(),
                dto.phone(),
                dto.birthDate()
        );

        return new PatientResponseDTO(
                patient.getId(),
                patient.getFullName(),
                patient.getPhone(),
                patient.getBirthDate()
        );
    }

    @Transactional
    public void desativarPaciente(UUID id) {

        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException(
                        "Paciente não encontrado!"
                ));

        patient.desativar();
        patient.getUser().desativar();
    }

    @Transactional
    public void reativarPaciente(UUID id) {

        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException(
                        "Paciente não encontrado!"
                ));

        patient.reativar();
        patient.getUser().reativar();
    }
}
