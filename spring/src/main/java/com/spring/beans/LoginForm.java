package com.spring.beans;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginForm {
    @NotEmpty(message = "{NotEmpty.Account.username}")
    private String username;

    @NotEmpty(message = "{NotEmpty.Account.password}")
    private String password;
}
