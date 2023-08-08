package com.zakaria.ecom.dtos.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@ToString
public class UserRequest {
    @NotNull
    @Size(min = 3, max = 150, message = "Name must be between 03 and 150 characters")
    private String name;
    @Email
    private String email;
    @NotNull
    @Size(min = 8, message = "Password must be at least 8 characters")
    private String password;
    @NotNull
    private ArrayList<Integer> roles;
}
