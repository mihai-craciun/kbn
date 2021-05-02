package com.mihaicraicun.kbn.kbn.model.forms;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class RegisterForm {

    @Pattern(regexp="^[a-zA-Z\\s\\-]*$", message = "Full name cannot contain digits or special characters!")
    @NotBlank(message = "Full name cannot be empty!")
    private String fullName;

    @Email(message="Please provide a valid email address")
    @NotBlank(message = "Email cannot be empty!")
    private String email;

    @Pattern(regexp="^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$", message = "The password must have minimum eight characters, at least one letter and one number")
    @Size(min = 6, max = 30, message = "Password must have between 6 and 30 characters!")
    private String password;

    @Pattern(regexp="^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$", message = "")
    @Size(min = 6, max = 30, message = "")
    private String passwordConfirm;
}