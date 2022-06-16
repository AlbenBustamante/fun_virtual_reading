package com.alnicode.funvirtualreading.persistence.mapper;

import com.alnicode.funvirtualreading.domain.dto.UserRequest;
import com.alnicode.funvirtualreading.domain.dto.UserResponse;
import com.alnicode.funvirtualreading.persistence.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


import static com.alnicode.funvirtualreading.util.AppConstants.DATE_FORMAT;
import static com.alnicode.funvirtualreading.util.AppConstants.DATE_TIME_FORMAT;

/**
 * The user mapper.
 *
 * @author Alben Bustamante
 * @version 1.0
 * @since 1.0
 */
@Mapper(componentModel = "spring", uses = {LikeMapper.class})
public interface UserMapper extends BaseMapper<UserResponse, User> {

    /**
     * Map a person entity to the person response DTO.
     *
     * @param entity the {@link User} entity to be mapped
     * @return the {@link UserResponse} DTO
     */
    @Mapping(target = "birthday", dateFormat = DATE_FORMAT)
    @Mapping(target = "date", dateFormat = DATE_TIME_FORMAT)
    @Mapping(target = "nationality", source = "nationality.name")
    UserResponse toResponse(User entity);

    /**
     * Map a person request DTO to the person entity.
     *
     * @param request the {@link UserRequest} DTO to be mapped
     * @return the {@link User} entity
     */
    @Mapping(target = "birthday", dateFormat = DATE_FORMAT)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "nationality", ignore = true)
    @Mapping(target = "date", ignore = true)
    @Mapping(target = "publishedBooks", ignore = true)
    @Mapping(target = "publishedComments", ignore = true)
    @Mapping(target = "collections", ignore = true)
    @Mapping(target = "likes", ignore = true)
    @Mapping(target = "roles", ignore = true)
    User toEntity(UserRequest request);
}
