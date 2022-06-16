package com.alnicode.funvirtualreading.domain.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * The user request DTO.
 *
 * @author Alben Bustamante
 * @version 1.0
 * @since 1.0
 */
@Data
public class UserRequest {
    @NotBlank
    private String firstname;

    @NotBlank
    private String lastname;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @NotBlank
    private String rpassword;

    @NotBlank
    private String birthday;

    @NotNull
    @Min(1L)
    private long nationalityId;

    /**
     * Check if the passwords do match.
     *
     * @return true if the passwords do match.
     */
    public boolean passwordsMatch() {
        return password.equals(rpassword);
    }
}
