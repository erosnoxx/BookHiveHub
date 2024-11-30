package com.book.hive.hub.presentation.controller;

import com.book.hive.hub.presentation.dto.request.*;
import com.book.hive.hub.presentation.dto.response.*;
import com.book.hive.hub.useCases.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.net.URI;

@RestController
@RequestMapping("auth")
public class AuthenticationController {
    @Autowired
    private LoginUseCase loginUseCase;
    @Autowired
    private RegisterUseCase registerUseCase;

    @Operation(
            summary = "Login",
            description = "Autentica o usuário fornecendo um token de autenticação",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Ok", content = @Content(mediaType = "application/json", schema = @Schema(implementation = LoginResponseDto.class))),
                    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDto.class))),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDto.class))),
                    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDto.class))),
                    @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDto.class)))
            }
    )
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(
            @RequestBody @Valid
            AuthenticationRequestDto data) {
        LoginResponseDto loginResponseDto = this.loginUseCase.execute(data);

        return ResponseEntity.ok(loginResponseDto);
    }

    @Operation(
            summary = "Register",
            description = "Registra o usuário fornecendo os dados necessários",
            responses = {
                    @ApiResponse(responseCode = "201",
                            description = "Created", content = @Content(mediaType = "application/json", schema = @Schema(implementation = RegisterResponseDto.class))),
                    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDto.class))),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDto.class))),
                    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDto.class))),
                    @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDto.class)))
            }
    )
    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDto> register(
            @RequestBody @Valid
            RegisterRequestDto data) {
        RegisterResponseDto registerResponseDto = this.registerUseCase.execute(data);
        String location = "/users/" + registerResponseDto.user_id();

        return ResponseEntity.created(URI.create(location)).body(registerResponseDto);
    }
}
