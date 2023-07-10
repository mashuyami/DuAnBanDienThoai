package com.spring.beans;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;


@Getter
@Setter
public class RegisterForm {
    @Length(max = 255, message = "{Length.Account.username}")
    @NotEmpty(message = "{NotEmpty.Account.username}")
    private String username;

    @Length(max = 255, message = "{Length.Account.fullname}")
    @NotEmpty(message = "{NotEmpty.Account.fullname}")
    private String fullname;

    @Length(min = 8, max = 255, message = "{Length.Account.password}")
    @NotEmpty(message = "{NotEmpty.Account.password}")
    private String password;

    private String rePassword;

    @Length(max = 255, message = "{Length.Account.email}")
    @NotEmpty(message = "{NotEmpty.Account.email}")
    @Email(message = "{Email.Account.email}")
    private String email;
}
