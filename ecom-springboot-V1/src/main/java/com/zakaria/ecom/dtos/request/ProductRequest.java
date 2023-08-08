package com.zakaria.ecom.dtos.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ProductRequest {
    @NotBlank
    private  String name;
    @NotBlank
    @Size(min = 10)
    private String description;
    @NotNull
    private Double price;
    @NotNull
    private Integer quantity;
    @NotBlank
    private String photo;
    @NotNull
    private Integer category;
}
