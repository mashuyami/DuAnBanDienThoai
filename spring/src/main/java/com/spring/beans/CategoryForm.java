package com.spring.beans;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CategoryForm {

    private Integer id;

    @NotEmpty(message = "{NotEmpty.Category.name}")
    private String name;
}
