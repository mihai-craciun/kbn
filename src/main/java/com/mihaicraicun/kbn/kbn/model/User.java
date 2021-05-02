package com.mihaicraicun.kbn.kbn.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

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
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "owner")
    private List<Task> createdTasks;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "asignee")
    private List<Task> assignedTasks;
    
    @Enumerated(EnumType.STRING)
    private Role role = Role.USER;

    private Date created;
    
    private Date updated;

    @PrePersist
    protected void onCreate() {
        created = new Date();
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
