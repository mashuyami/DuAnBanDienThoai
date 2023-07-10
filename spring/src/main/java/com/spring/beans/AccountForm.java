package com.spring.beans;

import com.spring.entities.AccountRole;
//import jakarta.validation.constraints.Email;
//import jakarta.validation.constraints.NotEmpty;
//import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

@Getter
@Setter
@ToString
public class AccountForm {
    private Integer id;

    @Length(max = 255, message = "{Length.Account.username}")
    @NotEmpty(message = "{NotEmpty.Account.username}")
    private String username;

    @Length(max = 255, message = "{Length.Account.fullname}")
    @NotEmpty(message = "{NotEmpty.Account.fullname}")
    private String fullname;

    @Length(min = 8, max = 255, message = "{Length.Account.password}")
    private String password;

    private String rePassword;

    @Length(max = 255, message = "{Length.Account.email}")
    @NotEmpty(message = "{NotEmpty.Account.email}")
    @Email(message = "{Email.Account.email}")
    private String email;

    @NotNull
    private Integer activated;

    @NotNull
    private AccountRole role;
    @NotNull(message = "Không được để trống avatar")
    private String photo;
}
