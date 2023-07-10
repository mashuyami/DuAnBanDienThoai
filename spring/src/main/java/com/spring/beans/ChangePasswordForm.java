package com.spring.beans;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@ToString
public class ChangePasswordForm {
    private String oldPassword;

    @Length(min = 8, max = 255, message = "{Length.Account.password}")
    @NotEmpty(message = "{NotEmpty.Account.password}")
    private String newPassword;

    private String rePassword;
}
