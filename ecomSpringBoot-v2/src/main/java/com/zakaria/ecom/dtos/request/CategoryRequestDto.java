package com.zakaria.ecom.dtos.request;

import com.zakaria.ecom.enums.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CategoryRequestDto {
    @NotNull
    @NotBlank
    @Size(min = 3)
    private String name;
    @NotNull
    private Status status;
}
