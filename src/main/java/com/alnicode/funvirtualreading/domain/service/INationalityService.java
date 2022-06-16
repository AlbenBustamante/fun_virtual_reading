package com.alnicode.funvirtualreading.domain.service;

import com.alnicode.funvirtualreading.domain.dto.NationalityRequest;
import com.alnicode.funvirtualreading.domain.dto.NationalityResponse;
import java.util.List;
import java.util.Optional;

/**
 * The nationality service template.
 *
 * @author Alben Bustamante
 * @version 1.0
 * @since 1.0
 */
public interface INationalityService extends ICrudService<NationalityRequest, NationalityResponse> {

    /**
     * Get a sorted list with all the responses registered.
     *
     * @return the sorted list
     */
    List<NationalityResponse> getAllOrderByCountry();

    /**
     * Get a nationality response by the country name.
     *
     * @param country the name to search
     * @return the {@link NationalityResponse} found
     */
    Optional<NationalityResponse> getByCountry(String country);

    /**
     * Get a nationality response by the person id.
     *
     * @param personId the id to search
     * @return the {@link NationalityResponse} found
     */
    Optional<NationalityResponse> getByAuthorId(long personId);

}
