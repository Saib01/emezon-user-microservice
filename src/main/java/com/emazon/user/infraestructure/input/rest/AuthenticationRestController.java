package com.emazon.user.infraestructure.input.rest;

import com.emazon.user.application.dtos.AuthResponse;
import com.emazon.user.application.dtos.AuthLoginRequest;
import com.emazon.user.application.handler.IAuthenticationHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.emazon.user.infraestructure.util.InfraestructureRestControllerConstants.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationRestController {
    private final IAuthenticationHandler authenticationHandler;
    @Operation(summary =USER_LOGIN)
    @ApiResponses(value = {
            @ApiResponse(responseCode = RESPONSE_CODE_SUCCESS, description = RESPONSE_DESCRIPTION_LOGIN_SUCCESSFUL, content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthResponse.class))),
            @ApiResponse(responseCode = RESPONSE_CODE_UNAUTHORIZED, description = RESPONSE_DESCRIPTION_UNAUTHORIZED, content = @Content)
    })
    @PostMapping
    public ResponseEntity<AuthResponse> login(@RequestBody AuthLoginRequest authLoginRequest){
        return new ResponseEntity<>(this.authenticationHandler.loginUser(authLoginRequest), HttpStatus.OK);
    }


}
