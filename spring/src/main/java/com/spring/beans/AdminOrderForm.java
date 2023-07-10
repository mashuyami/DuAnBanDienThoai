package com.spring.beans;

import com.spring.entities.Account;
import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
@Getter
@Setter
public class AdminOrderForm {
    private Integer id;

    @NotNull(message = "Không được để trống")
    private Account account;


    @NotEmpty(message = "{NotEmpty.Order.address}")
    private String address;

    private int orderStatus;
}
