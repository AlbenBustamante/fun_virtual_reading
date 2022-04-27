package com.alnicode.funvirtualreading.persistence.mapper;

import java.util.List;

import com.alnicode.funvirtualreading.domain.dto.NationalityRequest;
import com.alnicode.funvirtualreading.domain.dto.NationalityResponse;
import com.alnicode.funvirtualreading.persistence.entity.Nationality;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface NationalityMapper {
    NationalityResponse toResponse(Nationality entity);
    List<NationalityResponse> toResponses(List<Nationality> entities);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "persons", ignore = true)
    Nationality toEntity(NationalityRequest request);
}
