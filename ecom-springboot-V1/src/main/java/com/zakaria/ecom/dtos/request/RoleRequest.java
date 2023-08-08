package com.zakaria.ecom.dtos.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class RoleRequest {

    @NotEmpty
    @Size(min = 3)
    private String name;
}
