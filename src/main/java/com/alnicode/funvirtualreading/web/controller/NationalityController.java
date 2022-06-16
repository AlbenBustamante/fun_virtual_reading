package com.alnicode.funvirtualreading.web.controller;

import com.alnicode.funvirtualreading.domain.dto.NationalityRequest;
import com.alnicode.funvirtualreading.domain.dto.NationalityResponse;
import com.alnicode.funvirtualreading.domain.service.ICrudService;
import com.alnicode.funvirtualreading.domain.service.INationalityService;
import java.util.List;
import javax.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The nationality rest controller.
 *
 * @author Alben Bustamante
 * @version 1.0
 * @since 1.0
 */
@Validated
@RestController
@RequestMapping("/nationalities")
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
    @GetMapping("/sorted")
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
    public ResponseEntity<NationalityResponse> getByCountry(@NotBlank @PathVariable("country") String country) {
        return ResponseEntity.of(this.service.getByCountry(country));
    }
}
