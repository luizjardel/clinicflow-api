package br.com.clinicflow.api.user.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AccessLevel;

import java.util.UUID;
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false,length = 30)
    private Role role;

    @Column(nullable = false)
    private boolean active =  true;

    public User(String email, String password, Role role) {
        this.email = email;
        this.password = password;
        this.role = role;
        this.active = true;
    }
    public void desativar(){
        this.active = false;
    }
    public void reativar(){
        this.active = true;
    }

}