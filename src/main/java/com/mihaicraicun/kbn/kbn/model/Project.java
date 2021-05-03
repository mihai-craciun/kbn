package com.mihaicraicun.kbn.kbn.model;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Data
@Entity
@Table(name = "projects")
public class Project {

    @Id
    @Column(name = "id")
    @GenericGenerator(name = "uuid", strategy = "uuid4")
    private String id;

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "description", nullable = true, length = 1000)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="owner_id", nullable=false)
    private User owner;

    @Column(name = "created_at")
    private Date created;
    
    @Column(name = "updated_at")
    private Date updated;

    @Column(name = "private")
    private Boolean isPrivate;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "project")
    private List<Task> tasks;

    @PrePersist
    protected void onCreate() {
      created = new Date();
      this.setId(UUID.randomUUID().toString());
    }
  
    @PreUpdate
    protected void onUpdate() {
      updated = new Date();
    }
}
