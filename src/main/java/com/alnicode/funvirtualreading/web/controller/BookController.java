package com.alnicode.funvirtualreading.web.controller;

import java.util.List;

import javax.validation.constraints.Min;

import com.alnicode.funvirtualreading.domain.dto.BookRequest;
import com.alnicode.funvirtualreading.domain.dto.BookResponse;
import com.alnicode.funvirtualreading.domain.dto.PersonResponse;
import com.alnicode.funvirtualreading.domain.service.IBookService;
import com.alnicode.funvirtualreading.domain.service.ICrudService;
import com.alnicode.funvirtualreading.domain.service.IPersonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/books")
public class BookController extends CrudController<BookRequest, BookResponse> {
    @Autowired
    private IBookService service;

    @Autowired
    private IPersonService personService;

    @Override
    protected ICrudService<BookRequest, BookResponse> service() {
        return this.service;
    }
    
    @GetMapping("{id}/likedBy")
    public ResponseEntity<List<PersonResponse>> getPersonsWhoLikedIt(@Min(1L) @PathVariable("id") long id) {
        return ResponseEntity.of(this.personService.getByBooksLiked(id));
    }

    @GetMapping("/genre/{id}")
    public ResponseEntity<List<BookResponse>> getByGenre(@Min(1L) @PathVariable("id") long genreId) {
        return ResponseEntity.of(this.service.getByGenre(genreId));
    }

    @GetMapping("/{id}/author")
    public ResponseEntity<PersonResponse> getAuthor(@Min(1L) @PathVariable("id") long personId) {
        return ResponseEntity.of(this.personService.getByPublishedBook(personId));
    }
}
