package br.com.clinicflow.api.patient.repository;

import br.com.clinicflow.api.patient.domain.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PatientRepository extends JpaRepository<Patient, UUID> {

    boolean existsByPhone(String phone);

    boolean existsByPhoneAndIdNot(String phone, UUID id);

    List<Patient> findAllByActiveTrue();

    Optional<Patient> findByIdAndActiveTrue(UUID id);
}