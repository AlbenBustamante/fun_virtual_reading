package com.alnicode.funvirtualreading.domain.dto;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class NationalityRequest {
    @NotBlank
    private String name;

    @NotBlank
    private String country;
}
