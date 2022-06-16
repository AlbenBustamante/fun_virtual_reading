package com.alnicode.funvirtualreading.domain.dto;

import java.util.HashSet;
import java.util.Set;
import lombok.Data;

/**
 * The user response DTO.
 *
 * @author Alben Bustamante
 * @version 1.0
 * @since 1.0
 */
@Data
public class UserResponse {
    private long id;
    private String firstname, lastname, email, username, birthday, nationality, date;
    private Set<LikeResponse> likes = new HashSet<>();
}
