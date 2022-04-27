package com.alnicode.funvirtualreading.persistence.mapper;

import static com.alnicode.funvirtualreading.util.AppConstants.DATE_FORMAT;
import static com.alnicode.funvirtualreading.util.AppConstants.DATE_TIME_FORMAT;

import java.util.List;

import com.alnicode.funvirtualreading.domain.dto.PersonRequest;
import com.alnicode.funvirtualreading.domain.dto.PersonResponse;
import com.alnicode.funvirtualreading.persistence.entity.Person;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { BookMapper.class })
public interface PersonMapper {
    @Mapping(target = "birthday", dateFormat = DATE_FORMAT)
    @Mapping(target = "date", dateFormat = DATE_TIME_FORMAT)
    @Mapping(target = "nationality", source = "nationality.name")
    PersonResponse toResponse(Person entity);
    List<PersonResponse> toResponses(List<Person> entities);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "nationality", ignore = true)
    @Mapping(target = "date", ignore = true)
    @Mapping(target = "publishedBooks", ignore = true)
    @Mapping(target = "publishedComments", ignore = true)
    @Mapping(target = "collections", ignore = true)
    @Mapping(target = "likes", ignore = true)
    Person toEntity(PersonRequest request);
}
