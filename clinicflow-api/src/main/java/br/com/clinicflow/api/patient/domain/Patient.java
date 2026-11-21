package br.com.clinicflow.api.patient.domain;

import br.com.clinicflow.api.user.domain.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "patients")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false, unique = true,length = 20)
    private String phone;

    @Column(nullable = false)
    private LocalDate birthDate;

    @Column(nullable = false)
    private boolean active = true;

    public void desativar() {
        this.active = false;
    }

    @OneToOne
    @JoinColumn( name = "user_id",nullable = false,unique = true)
    private User user;

    public Patient(String fullName, String phone, LocalDate birthDate,User user) {
        this.fullName = fullName;
        this.phone = phone;
        this.birthDate = birthDate;
        this.user = user;
    }
    public void atualizarDados(String fullName, String phone, LocalDate birthDate) {
        this.fullName = fullName;
        this.phone = phone;
        this.birthDate = birthDate;
    }
    public void reativar(){
        this.active = true;
    }

}
