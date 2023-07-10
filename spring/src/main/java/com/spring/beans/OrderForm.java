package com.spring.beans;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderForm {
    @NotEmpty(message = "{NotEmpty.Order.address}")
    private String address;

}
