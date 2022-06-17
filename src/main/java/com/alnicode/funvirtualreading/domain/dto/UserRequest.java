package com.alnicode.funvirtualreading.domain.dto;

import com.alnicode.funvirtualreading.constants.UserConstants;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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

    @NotBlank(message = UserConstants.FIRST_NAME_BLANK)
    @Size(min = 3, max = 40, message = UserConstants.FIRST_NAME_SIZE)
    private String firstname;

    @NotBlank(message = UserConstants.LAST_NAME_BLANK)
    @Size(min = 3, max = 60, message = UserConstants.LAST_NAME_SIZE)
    private String lastname;

    @NotBlank(message = UserConstants.EMAIL_BLANK)
    @Email(message = UserConstants.EMAIL_FORMAT)
    @Size(min = 12, max = 200, message = UserConstants.EMAIL_SIZE)
    private String email;

    @NotBlank(message = UserConstants.USERNAME_BLANK)
    @Size(min = 3, max = 60, message = UserConstants.USERNAME_SIZE)
    private String username;

    @NotBlank(message = UserConstants.PASSWORD_BLANK)
    @Size(min = 8, max = 40, message = UserConstants.PASSWORD_SIZE)
    private String password;

    @NotBlank(message = UserConstants.RPASSWORD_BLANK)
    @Size(min = 8, max = 40, message = UserConstants.PASSWORD_SIZE)
    private String rpassword;

    @NotBlank(message = UserConstants.BIRTHDAY_BLANK)
    private String birthday;

    @NotNull(message = UserConstants.NATIONALITY_NULL)
    @Min(value = 1L, message = UserConstants.NATIONALITY_MIN)
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
