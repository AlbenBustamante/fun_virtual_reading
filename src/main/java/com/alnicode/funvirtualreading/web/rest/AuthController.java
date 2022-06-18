package com.alnicode.funvirtualreading.web.rest;

import com.alnicode.funvirtualreading.constants.UserConstants;
import com.alnicode.funvirtualreading.domain.dto.AuthenticationRequest;
import com.alnicode.funvirtualreading.domain.dto.AuthenticationResponse;
import com.alnicode.funvirtualreading.web.config.security.jwt.JWTUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The authentication rest controller to get the JWT.
 *
 * @author Alben Bustamante
 * @version 1.0
 * @since 1.0
 */
@RestController
@RequestMapping(UserConstants.AUTH_PATH)
@Validated
public class AuthController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    @Qualifier(UserConstants.USER_DETAILS_SERVICE)
    private UserDetailsService userDetailsService;

    @Autowired
    private JWTUtil jwtUtil;

    /**
     * Checks if the user is valid to authenticate and generate a new token.
     *
     * @param request the user to check
     * @return a {@link AuthenticationResponse} with the JWT
     */
    @PostMapping
    @ApiOperation("Generate and get your token")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Something went wrong"),
            @ApiResponse(code = 403, message = "Bad credentials, try again")})
    public ResponseEntity<AuthenticationResponse> generateToken(@NotNull @Valid @RequestBody AuthenticationRequest request) {
        try {
            manager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
            final var userDetails = userDetailsService.loadUserByUsername(request.getUsername());
            final var jwt = jwtUtil.generateToken(userDetails);

            return ResponseEntity.ok(new AuthenticationResponse(jwt));
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

}
