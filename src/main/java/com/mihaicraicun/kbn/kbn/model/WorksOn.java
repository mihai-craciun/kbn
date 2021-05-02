package com.mihaicraicun.kbn.kbn.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import com.mihaicraicun.kbn.kbn.model.embeds.UserProjectKey;

import lombok.Data;

@Entity
@Data
public class WorksOn {

    @EmbeddedId
    UserProjectKey key;

    @ManyToOne
    @MapsId("userEmail")
    User user;

    @ManyToOne
    @MapsId("projectId")
    Project project;
}
