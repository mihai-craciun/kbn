package com.mihaicraicun.kbn.kbn.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class User {

    @Id
    private String email;

    private String fullName;

    private String password;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Project> projects;

    @Enumerated(EnumType.STRING)
    private Role role = Role.USER;

    public enum Role {
        USER,
        ADMIN;
    }
}
