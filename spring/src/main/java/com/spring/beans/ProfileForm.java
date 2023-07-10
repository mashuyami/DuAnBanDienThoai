package com.spring.beans;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
@Getter
@Setter
public class ProfileForm {
    @Length(max = 255, message = "{Length.Account.fullname}")
    @NotEmpty(message = "{NotEmpty.Account.fullname}")
    private String fullname;

    @Length(max = 255, message = "{Length.Account.email}")
    @NotEmpty(message = "{NotEmpty.Account.email}")
    @Email(message = "{Email.Account.email}")
    private String email;

}
