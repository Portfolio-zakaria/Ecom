package com.zakaria.ecom.dtos.request;

import com.zakaria.ecom.enums.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProductRequestDto {
    @NotNull
    @NotBlank
    @Size(min = 3)
    private String name;
    @NotNull
    @NotBlank
    @Size(min = 3)
    private String description;
    @NotNull
    private Double price;
    @NotNull
    private int quantity;
    @NotNull
    private MultipartFile photo;
    @NotNull
    private Status status;
    @NotNull
    private String category;
}
