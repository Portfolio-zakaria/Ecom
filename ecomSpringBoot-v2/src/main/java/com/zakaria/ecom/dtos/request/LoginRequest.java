package com.zakaria.ecom.dtos.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class LoginRequest {
    @NotNull
    private String grantType;
    @NonNull
    private String username;
    @NotNull
    private String password;
    @NotNull
    private boolean withRefreshToken;
    private String refreshToken;
}
