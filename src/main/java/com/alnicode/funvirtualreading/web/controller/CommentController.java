package com.alnicode.funvirtualreading.web.controller;

import java.util.List;

import javax.validation.constraints.Min;

import com.alnicode.funvirtualreading.domain.dto.BookResponse;
import com.alnicode.funvirtualreading.domain.dto.CommentRequest;
import com.alnicode.funvirtualreading.domain.dto.CommentResponse;
import com.alnicode.funvirtualreading.domain.service.IBookService;
import com.alnicode.funvirtualreading.domain.service.ICommentService;
import com.alnicode.funvirtualreading.domain.service.ICrudService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/comments")
public class CommentController extends CrudController<CommentRequest, CommentResponse> {
    @Autowired
    private ICommentService service;

    @Autowired
    private IBookService bookService;

    @Override
    protected ICrudService<CommentRequest, CommentResponse> service() {
        return this.service;
    }

    @GetMapping("/{id}/book")
    public ResponseEntity<BookResponse> getBook(@Min(1L) @PathVariable("id") long commentId) {
        return ResponseEntity.of(this.bookService.getByComment(commentId));
    }

    @GetMapping("/person/{id}")
    public ResponseEntity<List<CommentResponse>> getAllByPerson(@Min(1L) @PathVariable("id") long personId) {
        return ResponseEntity.of(this.service.getByPerson(personId));
    }

}
