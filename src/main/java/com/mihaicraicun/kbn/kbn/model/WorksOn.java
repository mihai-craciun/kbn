package com.mihaicraicun.kbn.kbn.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.mihaicraicun.kbn.kbn.model.embeds.UserProjectKey;

import lombok.Data;

@Data
@Entity
@Table(name = "works_on")
public class WorksOn {

    @EmbeddedId
    UserProjectKey key;

    @ManyToOne
    @MapsId("userId")
    User user;

    @ManyToOne
    @MapsId("projectId")
    Project project;
}
