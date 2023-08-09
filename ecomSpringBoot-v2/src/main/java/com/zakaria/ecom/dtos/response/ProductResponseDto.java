package com.zakaria.ecom.dtos.response;

import com.zakaria.ecom.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ProductResponseDto {
    private String name;
    private String description;
    private Double price;
    private int sold;
    private int quantity;
    private String photo;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Status status;
    private CategoryResponseDto category;
}
