package com.emazon.user.infraestructure.input.rest;

import com.emazon.user.application.dtos.AuthLoginRequest;
import com.emazon.user.application.dtos.AuthResponse;
import com.emazon.user.application.dtos.JwtPayloadResponse;
import com.emazon.user.application.handler.IAuthenticationHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.emazon.user.infraestructure.util.InfraestructureRestControllerConstants.*;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationRestController {
    private final IAuthenticationHandler authenticationHandler;

    @Operation(summary = USER_LOGIN)
    @ApiResponses(value = {
            @ApiResponse(responseCode = RESPONSE_CODE_SUCCESS, description = RESPONSE_DESCRIPTION_LOGIN_SUCCESSFUL, content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = AuthResponse.class))),
            @ApiResponse(responseCode = RESPONSE_CODE_UNAUTHORIZED, description = RESPONSE_DESCRIPTION_UNAUTHORIZED, content = @Content)
    })
    @PostMapping
    public ResponseEntity<AuthResponse> login(@RequestBody AuthLoginRequest authLoginRequest) {
        return new ResponseEntity<>(this.authenticationHandler.loginUser(authLoginRequest), HttpStatus.OK);
    }

    @Operation(summary = USER_ME)
    @ApiResponses(value = {
            @ApiResponse(responseCode = RESPONSE_CODE_SUCCESS, description = RESPONSE_DESCRIPTION_USER_ME,
                    content = @Content(schema = @Schema(implementation = JwtPayloadResponse.class))),
            @ApiResponse(responseCode = RESPONSE_CODE_UNAUTHORIZED, description = RESPONSE_DESCRIPTION_UNAUTHORIZED, content = @Content)
    })
    @GetMapping("/me")
    public ResponseEntity<JwtPayloadResponse> getJwtPayload() {
        return new ResponseEntity<>(this.authenticationHandler.getJwtPayload(), HttpStatus.OK);
    }
}
