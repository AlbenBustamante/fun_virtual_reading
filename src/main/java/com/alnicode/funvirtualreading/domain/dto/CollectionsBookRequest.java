package com.alnicode.funvirtualreading.domain.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class CollectionsBookRequest {
    @NotNull
    @Min(1)
    @Max(10)
    private Integer rating;

    @NotBlank
    private String note;
}
