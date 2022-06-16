package com.alnicode.funvirtualreading.domain.dto;

import lombok.Data;

/**
 * The nationality response DTO.
 *
 * @author Alben Bustamante
 * @version 1.0
 * @since 1.0
 */
@Data
public class NationalityResponse {
    private long id;
    private String name;
    private String country;
}
