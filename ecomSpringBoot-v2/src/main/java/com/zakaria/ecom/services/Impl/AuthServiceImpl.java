package com.zakaria.ecom.services.Impl;

import com.zakaria.ecom.dtos.request.LoginRequest;
import com.zakaria.ecom.dtos.request.SignupRequest;
import com.zakaria.ecom.exceptions.ResourceNotFoundException;
import com.zakaria.ecom.models.AppRole;
import com.zakaria.ecom.models.AppUser;
import com.zakaria.ecom.repositories.AppRoleRepository;
import com.zakaria.ecom.services.AccountService;
import com.zakaria.ecom.services.AuthService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class AuthServiceImpl implements AuthService {

    private JwtEncoder jwtEncoder;
    private JwtDecoder jwtDecoder;
    private AuthenticationManager authenticationManager;
    private AccountService accountService;
    private AppRoleRepository appRoleRepository;

    public AuthServiceImpl(JwtEncoder jwtEncoder, JwtDecoder jwtDecoder, AuthenticationManager authenticationManager, AccountService accountService) {
        this.jwtEncoder = jwtEncoder;
        this.jwtDecoder = jwtDecoder;
        this.authenticationManager = authenticationManager;
        this.accountService = accountService;
    }

    @Override
    public ResponseEntity<Map<String, String>> login(LoginRequest loginRequest) {
        String scope = null;
        String subject = null;
        if (loginRequest.getGrantType().equals("password")) {
            Authentication authentication = this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
            subject = authentication.getName();
            scope = authentication.getAuthorities()
                    .stream().map(auth -> auth.getAuthority())
                    .collect(Collectors.joining(" "));
        } else if (loginRequest.getGrantType().equals("refreshToken")) {
            if(loginRequest.getRefreshToken() == null) {
                return new ResponseEntity<>(Map.of("errorMessage","Refresh  Token is required"), HttpStatus.UNAUTHORIZED);
            }
            Jwt decodeJWT = null;
            try {
                decodeJWT = jwtDecoder.decode(loginRequest.getRefreshToken());
            } catch (JwtException e) {
                return new ResponseEntity<>(Map.of("errorMessage",e.getMessage()), HttpStatus.UNAUTHORIZED);
            }
            subject = decodeJWT.getSubject();
            AppUser user = accountService.loadUserByUsername(subject);
            scope =  user.getRoles().stream().map(r -> r.getName()).collect(Collectors.joining(" "));
        }
        Map<String, String> idToken = new HashMap<>();

        Instant instant = Instant.now();
        JwtClaimsSet jwtClaimsSet = JwtClaimsSet.builder()
                .subject(subject)
                .issuedAt(instant)
                .expiresAt(instant.plus(loginRequest.isWithRefreshToken() ? 30 : 60, ChronoUnit.MINUTES))
                .issuer("security-service")
                .claim("scope", scope)
                .build();
        String jwtAccessToken = jwtEncoder.encode(JwtEncoderParameters.from(jwtClaimsSet)).getTokenValue();

        idToken.put("access-token", jwtAccessToken);
        if (loginRequest.isWithRefreshToken()) {
            JwtClaimsSet jwtClaimsSetRefresh = JwtClaimsSet.builder()
                    .subject(subject)
                    .issuedAt(instant)
                    .expiresAt(instant.plus(90, ChronoUnit.MINUTES))
                    .issuer("security-service")
                    .build();
            String jwtRefreshToken = jwtEncoder.encode(JwtEncoderParameters.from(jwtClaimsSetRefresh)).getTokenValue();
            idToken.put("refresh-token", jwtRefreshToken);
        }
        return new ResponseEntity<>(idToken,HttpStatus.OK);
    }

    @Override
    public boolean signup(SignupRequest signupRequest) {
        AppUser appUser = accountService.loadUserByUsername(signupRequest.getUsername());
        if (appUser != null) {
            throw new RuntimeException("User already exist");
        }
        AppRole appRole = appRoleRepository.findByName("USER");

        appUser = accountService.addNewUser(signupRequest.getUsername(), signupRequest.getPassword(), signupRequest.getEmail(), signupRequest.getPassword());
        accountService.attachRoleToUser(appUser.getUsername(), appRole.getName());
        return true;
    }
}
