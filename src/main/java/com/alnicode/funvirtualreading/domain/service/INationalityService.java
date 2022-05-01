package com.alnicode.funvirtualreading.domain.service;

import java.util.List;
import java.util.Optional;

import com.alnicode.funvirtualreading.domain.dto.NationalityRequest;
import com.alnicode.funvirtualreading.domain.dto.NationalityResponse;

public interface INationalityService extends ICrudService<NationalityRequest, NationalityResponse> {
    List<NationalityResponse> getAllOrderByCountry();
    Optional<NationalityResponse> getByCountry(String country);
}
