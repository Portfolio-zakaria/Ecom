package com.zakaria.ecom.dtos.request;

import com.zakaria.ecom.models.Role;
import com.zakaria.ecom.models.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class RegisterDto {

    @NotNull(message = "Name is Required .")
    @Size(min = 3, max = 150, message = "Name must be between 03 and 150 characters")
    private String name;
    @Email(message = "Email should be valid")
    private String email;
    @NotNull(message = "Password is Required .")
    @Size(min = 8, message = "Password must be at least 8 characters")
    private String password;
    private List roles;




}
