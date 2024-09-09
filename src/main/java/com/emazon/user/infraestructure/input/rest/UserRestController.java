package com.emazon.user.infraestructure.input.rest;
import com.emazon.user.application.dtos.UserBasicRequest;
import com.emazon.user.application.dtos.UserRequest;
import com.emazon.user.application.handler.IUserHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static com.emazon.user.infraestructure.util.InfraestructureRestControllerConstants.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserRestController {
    private final IUserHandler userHandler;

    @Operation(summary = ADD_NEW_USER)
    @ApiResponses(value = {
            @ApiResponse(responseCode = RESPONSE_CODE_CREATED, description = RESPONSE_DESCRIPTION_USER_CREATED, content = @Content),
            @ApiResponse(responseCode = RESPONSE_CODE_CONFLICT, description = RESPONSE_DESCRIPTION_USER_ALREADY_EXISTS, content = @Content)
    })
    @PostMapping("/aux")
    ResponseEntity<Void> saveUser(@RequestBody UserBasicRequest userBasicRequest) {
        UserRequest userRequest=UserRequest.from(userBasicRequest);
        userHandler.saveAuxUser(userRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
