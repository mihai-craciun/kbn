package com.mihaicraicun.kbn.kbn.model.embeds;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class UserProjectKey implements Serializable {
    String projectId;
    String userId;
}
