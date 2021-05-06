package com.mihaicraciun.kbn.kbn.model;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "id")
    @GenericGenerator(name = "uuid", strategy = "uuid4")
    private String id;

    @NotNull
    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "password")
    private String password;
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "owner")
    private List<Project> projects;
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "owner")
    private List<Task> createdTasks;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "asignee")
    private List<Task> assignedTasks;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role = Role.USER;

    @Column(name = "created_at")
    private Date created;
    
    @Column(name = "updated_at")
    private Date updated;

    @PrePersist
    protected void onCreate() {
        created = new Date();
        this.setId(UUID.randomUUID().toString());
    }
    
    @PreUpdate
    protected void onUpdate() {
        updated = new Date();
    }

    public enum Role {
        USER,
        ADMIN;
    }
}
