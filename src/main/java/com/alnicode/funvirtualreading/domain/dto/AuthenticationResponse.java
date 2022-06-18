package com.alnicode.funvirtualreading.domain.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * The authentication response DTO.
 *
 * @author Alben Bustamante
 * @since 1.0
 * @version 1.0
 */
@RequiredArgsConstructor
@Getter
public class AuthenticationResponse {

    private final String jwt;

}
