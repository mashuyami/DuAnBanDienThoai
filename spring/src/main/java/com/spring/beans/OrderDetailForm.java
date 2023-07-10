package com.spring.beans;
import com.spring.entities.Order;
import com.spring.entities.Product;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDetailForm {
    private Integer id;

    @NotNull(message = "{NotNull.OrderDetail.order}")
    private Order order;

    @NotNull(message = "{NotNull.OrderDetail.product}")
    private Product product;

    @Min(value = 1, message = "{Min.OrderDetail.price}")
    @NotNull(message = "{NotNull.OrderDetail.price}")
    private Double price;

    @Min(value = 1, message = "{Min.OrderDetail.quantity}")
    @NotNull(message = "{NotNull.OrderDetail.quantity}")
    private Integer quantity;
}
