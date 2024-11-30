package com.book.hive.hub.useCases;

import com.book.hive.hub.configuration.security.TokenService;
import com.book.hive.hub.domain.entities.UserEntity;
import com.book.hive.hub.infra.services.UserService;
import com.book.hive.hub.presentation.dto.request.AuthenticationRequestDto;
import com.book.hive.hub.presentation.dto.response.LoginResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Map;

@Service
public class LoginUseCase {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserService userService;
    @Autowired
    private TokenService tokenService;

    public LoginResponseDto execute(AuthenticationRequestDto data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(
                data.username(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        Map<String, Object> tokenResponse = this.tokenService
                .generateToken((UserEntity) auth.getPrincipal());

        String token = (String) tokenResponse.get("token");
        Instant expiresAt = (Instant) tokenResponse.get("expiresAt");

        return new LoginResponseDto(token, expiresAt);
    }

}
