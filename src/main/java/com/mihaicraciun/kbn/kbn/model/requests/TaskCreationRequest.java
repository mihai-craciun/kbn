package com.mihaicraciun.kbn.kbn.model.requests;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mihaicraciun.kbn.kbn.model.Task.PriorityType;
import com.mihaicraciun.kbn.kbn.model.Task.TaskType;

import lombok.Data;

@Data
public class TaskCreationRequest implements Serializable {

    @JsonProperty("projectId")
    @NotNull
    private String projectId;
    
    @JsonProperty("name")
    @NotNull(message = "The title cannot be empty")
    @Size(min = 1, max = 255, message = "The title should not be empty and should contain maximum 255 characters.")
    private String name;

    @JsonProperty("description")
    @Size(min = 0, max = 1000, message = "The description should contain maximum 1000 characters")
    private String description;

    @JsonProperty("taskType")
    private TaskType taskType;

    @JsonProperty("priorityType")
    private PriorityType priorityType;

    @JsonProperty("asigneeEmail")
    private String asigneeEmail;

    @JsonProperty("storyPoints")
    private Integer storyPoints;

}
