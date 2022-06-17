package com.alnicode.funvirtualreading.domain.dto;

import com.alnicode.funvirtualreading.constants.NationalityConstants;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Data;

/**
 * The nationality request DTO.
 *
 * @author Alben Bustamante
 * @version 1.0
 * @since 1.0
 */
@Data
public class NationalityRequest {

    @NotBlank(message = NationalityConstants.NAME_BLANK)
    @Size(min = 5, max = 80, message = NationalityConstants.NAME_SIZE)
    private String name;

    @NotBlank(message = NationalityConstants.COUNTRY_BLANK)
    @Size(min = 3, max = 70, message = NationalityConstants.COUNTRY_SIZE)
    private String country;

}
