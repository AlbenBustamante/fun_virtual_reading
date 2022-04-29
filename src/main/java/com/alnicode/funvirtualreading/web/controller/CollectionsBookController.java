package com.alnicode.funvirtualreading.web.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import com.alnicode.funvirtualreading.domain.dto.CollectionsBookRequest;
import com.alnicode.funvirtualreading.domain.dto.CollectionsBookResponse;
import com.alnicode.funvirtualreading.domain.service.ICollectionsBookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
public class CollectionsBookController {
    @Autowired
    private ICollectionsBookService service;

    @GetMapping("/collection_books")
    public ResponseEntity<List<CollectionsBookResponse>> getAll() {
        return ResponseEntity.ok(this.service.getAll());
    }

    @GetMapping("/collection/{collectionId}/book/{bookId}")
    public ResponseEntity<CollectionsBookResponse> get(
            @Min(1L) @PathVariable("collectionId") long collectionId,
            @Min(1L) @PathVariable("bookId") long bookId) {
        return ResponseEntity.of(this.service.get(collectionId, bookId));
    }

    @PostMapping("/collection_books")
    public ResponseEntity<CollectionsBookResponse> register(@Valid @RequestBody CollectionsBookRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(request));
    }

    @PutMapping("/collection/{collectionId}/book/{bookId}")
    public ResponseEntity<CollectionsBookResponse> update(
            @Min(1L) @PathVariable("collectionId") long collectionId,
            @Min(1L) @PathVariable("bookId") long bookId,
            @Valid @RequestBody CollectionsBookRequest request) {
        return ResponseEntity.of(this.service.update(collectionId, bookId, request));
    }

    @DeleteMapping("/collection/{collectionId}/book/{bookId}")
    public ResponseEntity<CollectionsBookResponse> delete(
            @Min(1L) @PathVariable("collectionId") long collectionId,
            @Min(1L) @PathVariable("bookId") long bookId) {
        return this.service.delete(collectionId, bookId) ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}
