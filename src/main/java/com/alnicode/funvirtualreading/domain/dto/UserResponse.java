package com.alnicode.funvirtualreading.domain.dto;

import java.util.HashSet;
import java.util.Set;
import lombok.Data;

/**
 * The person response DTO.
 *
 * @author Alben Bustamante
 * @version 1.0
 * @since 1.0
 */
@Data
public class UserResponse {
    private long id;
    private String name;
    private String lastname;
    private String email;
    private String birthday;
    private String nationality;
    private String date;
    private Set<LikeResponse> likes = new HashSet<>();
}