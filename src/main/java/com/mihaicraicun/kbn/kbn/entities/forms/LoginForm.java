package com.mihaicraicun.kbn.kbn.entities.forms;

import lombok.Data;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class LoginForm {
    
    @Email(message = "Please provide a valid email address!")
    @NotBlank(message = "The email cannot be empty!")
    public String email;
    @NotBlank(message = "The password cannot be empty!")
    public String password;
}
