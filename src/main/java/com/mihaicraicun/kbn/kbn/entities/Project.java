package com.mihaicraicun.kbn.kbn.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Entity
@Data
public class Project {

    @Id
    @Column(name = "id")
    @GenericGenerator(name = "uuid", strategy = "uuid4")
    private String id;

    private String name;

    @Column(nullable = true)
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    private User owner;

}
