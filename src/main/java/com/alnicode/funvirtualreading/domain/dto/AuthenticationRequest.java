package com.alnicode.funvirtualreading.domain.dto;

import com.alnicode.funvirtualreading.constants.UserConstants;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Data;

/**
 * The authentication request DTO.
 *
 * @author Alben Bustamante
 * @since 1.0
 * @version 1.0
 */
@Data
public class AuthenticationRequest {

    @NotBlank(message = UserConstants.USERNAME_BLANK)
    @Size(min = 3, max = 60, message = UserConstants.USERNAME_SIZE)
    private String username;

    @NotBlank(message = UserConstants.PASSWORD_BLANK)
    @Size(min = 8, max = 40, message = UserConstants.PASSWORD_SIZE)
    private String password;

}
