package com.zakaria.ecom.dtos.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SignupRequest {

    @NonNull
    private String username;
    @NotNull
    private String password;
    @NotNull
    @Email
    private String email;
}
