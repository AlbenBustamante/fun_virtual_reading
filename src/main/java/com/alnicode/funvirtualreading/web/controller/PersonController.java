package com.alnicode.funvirtualreading.web.controller;

import javax.validation.constraints.Min;

import com.alnicode.funvirtualreading.domain.dto.PersonRequest;
import com.alnicode.funvirtualreading.domain.dto.PersonResponse;
import com.alnicode.funvirtualreading.domain.service.ICrudService;
import com.alnicode.funvirtualreading.domain.service.IPersonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/persons")
public class PersonController extends CrudController<PersonRequest, PersonResponse> {
    @Autowired
    private IPersonService service;

    @Override
    protected ICrudService<PersonRequest, PersonResponse> service() {
        return this.service;
    }

    @PostMapping("/{id}/book/{bookId}")
    public ResponseEntity<PersonResponse> addLike(
            @Min(1L) @PathVariable("id") long personId,
            @Min(1L) @PathVariable("bookId") long bookId) {
        return ResponseEntity.of(this.service.addLike(personId, bookId));
    }

    @DeleteMapping("/{id}/book/{bookId}")
    public ResponseEntity<PersonResponse> removeLike(
            @Min(1L) @PathVariable("id") long personId,
            @Min(1L) @PathVariable("bookId") long bookId) {
        return ResponseEntity.of(this.service.removeLike(personId, bookId));
    }
    
}
