package com.alnicode.funvirtualreading.web.rest;

import com.alnicode.funvirtualreading.constants.NationalityConstants;
import com.alnicode.funvirtualreading.domain.dto.NationalityRequest;
import com.alnicode.funvirtualreading.domain.dto.NationalityResponse;
import com.alnicode.funvirtualreading.domain.service.ICrudService;
import com.alnicode.funvirtualreading.domain.service.INationalityService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;
import java.util.List;
import javax.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import static com.alnicode.funvirtualreading.constants.SwaggerConstants.API_KEY_NAME;

/**
 * The nationality rest controller.
 *
 * @author Alben Bustamante
 * @version 1.0
 * @since 1.0
 */
@Validated
@RestController
@RequestMapping(NationalityConstants.MAIN_PATH)
public class NationalityController extends CrudController<NationalityRequest, NationalityResponse> {
    @Autowired
    private INationalityService service;

    @Override
    protected ICrudService<NationalityRequest, NationalityResponse> service() {
        return this.service;
    }

    /**
     * Get a sorted list of all the nationalities registered.
     *
     * @return a {@link ResponseEntity} with the nationalities list
     */
    @GetMapping(NationalityConstants.SORTED_PATH)
    @ApiOperation(value = "Get a sorted list of all the registered nationalities", authorizations = {@Authorization(API_KEY_NAME)})
    @ApiResponse(code = 200, message = "OK")
    public ResponseEntity<List<NationalityResponse>> getAllSorted() {
        return ResponseEntity.ok(this.service.getAllOrderByCountry());
    }

    /**
     * Get a nationality by the country name.
     *
     * @param country the name to search
     * @return a {@link ResponseEntity} with the nationality found
     */
    @GetMapping("/country/{country}")
    @ApiOperation(value = "Introduce a country and get the nationality", authorizations = {@Authorization(API_KEY_NAME)})
    @ApiResponses({
            @ApiResponse(code = 200, message = "Nationality found!"),
            @ApiResponse(code = 400, message = "Something went wrong"),
            @ApiResponse(code = 404, message = "Nationality not found")})
    public ResponseEntity<NationalityResponse> getByCountry(@NotBlank @PathVariable("country") String country) {
        return ResponseEntity.of(this.service.getByCountry(country));
    }
}
