package com.spring.beans;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class ResetPasswordForm {

    private String token;

    @Length(min = 8, max = 255, message = "{Length.Account.password}")
    @NotEmpty(message = "{NotEmpty.Account.password}")
    private String password;

    private String rePassword;
}
