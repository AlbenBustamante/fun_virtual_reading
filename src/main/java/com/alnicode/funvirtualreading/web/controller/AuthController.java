package com.alnicode.funvirtualreading.web.controller;

import com.alnicode.funvirtualreading.constants.UserConstants;
import com.alnicode.funvirtualreading.domain.dto.AuthenticationRequest;
import com.alnicode.funvirtualreading.domain.dto.AuthenticationResponse;
import com.alnicode.funvirtualreading.web.config.security.jwt.JWTUtil;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
 * @since 1.0
 * @version 1.0
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
    public ResponseEntity<AuthenticationResponse> generateToken(@NotNull @Valid @RequestBody AuthenticationRequest request) {
        try {
            manager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
            final var userDetails = userDetailsService.loadUserByUsername(request.getUsername());
            final var jwt = jwtUtil.generateToken(userDetails);

            return ResponseEntity.ok(new AuthenticationResponse(jwt));
        } catch (BadCredentialsException ex) {
            return ResponseEntity.badRequest().build();
        }
    }

}
