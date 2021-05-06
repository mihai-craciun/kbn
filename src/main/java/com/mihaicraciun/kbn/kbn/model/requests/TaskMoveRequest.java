package com.mihaicraciun.kbn.kbn.model.requests;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mihaicraciun.kbn.kbn.model.Task.State;

import lombok.Data;

@Data
public class TaskMoveRequest implements Serializable {
    @JsonProperty("id")
    @NotNull
    private String id;

    @JsonProperty("taskState")
    @NotNull
    private State taskState;
}
