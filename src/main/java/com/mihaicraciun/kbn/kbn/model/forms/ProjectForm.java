package com.mihaicraciun.kbn.kbn.model.forms;

import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class ProjectForm {
    
    @NotBlank(message = "The name cannot be empty")
    @Pattern(regexp = "^[a-zA-Z0-9\\_\\-]+$", message = "The name can only contain letters, digits, underscores and hyphens.")
    public String name;

    @Size(min = 0, max = 1000, message = "The description can contain 1000 characters maximum.")
    public String description;

    public Boolean isPrivate;
}

