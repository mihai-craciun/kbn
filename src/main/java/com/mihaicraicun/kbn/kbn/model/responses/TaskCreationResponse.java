package com.mihaicraicun.kbn.kbn.model.responses;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class TaskCreationResponse implements Serializable {

    @JsonProperty("hasErrors")
    Boolean hasErrors;

    @JsonProperty("errors")
    Map<String, List<String>> errors;
    
}
