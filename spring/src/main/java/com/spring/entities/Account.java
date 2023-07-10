package com.spring.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "accounts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Account implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "fullname", nullable = false)
    private String fullname;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "photo")
    private String photo;

    @Column(name = "activated", nullable = false)
    private Integer activated;

    @Column(name = "role", nullable = false)
    private AccountRole role;

    @Column(name = "reset_password_token")
    private String resetPasswordToken;

    @OneToMany(mappedBy = "account")
    private List<Order> orders;
}
