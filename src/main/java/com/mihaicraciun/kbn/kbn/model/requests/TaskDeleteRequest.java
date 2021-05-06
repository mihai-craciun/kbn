package com.mihaicraciun.kbn.kbn.model.requests;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class TaskDeleteRequest implements Serializable {
    @JsonProperty("id")
    @NotNull
    private String id;    
}
