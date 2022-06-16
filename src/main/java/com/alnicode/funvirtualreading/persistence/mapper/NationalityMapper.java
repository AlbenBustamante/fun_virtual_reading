package com.alnicode.funvirtualreading.persistence.mapper;

import com.alnicode.funvirtualreading.domain.dto.NationalityRequest;
import com.alnicode.funvirtualreading.domain.dto.NationalityResponse;
import com.alnicode.funvirtualreading.persistence.entity.Nationality;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * The nationality mapper.
 *
 * @author Alben Bustamante
 * @version 1.0
 * @since 1.0
 */
@Mapper(componentModel = "spring")
public interface NationalityMapper {

    /**
     * Map a nationality entity to the nationality response DTO.
     *
     * @param entity the {@link Nationality} entity to be mapped
     * @return the {@link NationalityResponse} DTO
     */
    NationalityResponse toResponse(Nationality entity);

    /**
     * Map an entities list to the responses list.
     *
     * @param entities the list to be mapped
     * @return the responses list
     */
    List<NationalityResponse> toResponses(List<Nationality> entities);

    /**
     * Map a nationality request DTO to the nationality entity.
     *
     * @param request the {@link NationalityRequest} to be mapped
     * @return the {@link Nationality} entity
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "users", ignore = true)
    Nationality toEntity(NationalityRequest request);
}
