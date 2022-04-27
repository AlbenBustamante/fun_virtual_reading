package com.alnicode.funvirtualreading.domain.dto;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class GenreRequest {
    @NotBlank
    private String name;

    @NotBlank
    private String description;
}
