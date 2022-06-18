package com.alnicode.funvirtualreading.web.controller;

import com.alnicode.funvirtualreading.domain.dto.CollectionsBookRequest;
import com.alnicode.funvirtualreading.domain.dto.CollectionsBookResponse;
import com.alnicode.funvirtualreading.domain.service.ICollectionsBookService;
import com.alnicode.funvirtualreading.exception.RegisterNotValidException;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.Min;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import static com.alnicode.funvirtualreading.constants.CollectionsBookConstants.COLLECTIONS_BOOKS_PATH;
import static com.alnicode.funvirtualreading.constants.CollectionsBookConstants.MAIN_PATH;

/**
 * The collections-books rest controller.
 *
 * @author Alben Bustamante
 * @version 1.0
 * @since 1.0
 */
@Validated
@RestController
public class CollectionsBookController {
    @Autowired
    private ICollectionsBookService service;

    /**
     * Get all the collections-books registered.
     * <p>Optional: You can put a boolean param to the url to get a sorted list.</p>
     *
     * @param rating true to get the sorted list or nothing to get the default list
     * @return a {@link ResponseEntity} with the responses list
     */
    @GetMapping(MAIN_PATH)
    public ResponseEntity<List<CollectionsBookResponse>> getAll(@RequestParam(required = false) boolean rating) {
        return ResponseEntity.ok(rating ? service.getAll() : service.getAllOrderByRating());
    }

    /**
     * Get a collections-books by the composite pk.
     *
     * @param collectionId the collection id
     * @param bookId       the book id
     * @return a {@link ResponseEntity} with the response found
     */
    @GetMapping(COLLECTIONS_BOOKS_PATH)
    public ResponseEntity<CollectionsBookResponse> get(@Min(1L) @PathVariable("collectionId") long collectionId,
                                                       @Min(1L) @PathVariable("bookId") long bookId) {
        return ResponseEntity.of(service.get(collectionId, bookId));
    }

    /**
     * Register a new collections-books by the composite pk.
     *
     * @param collectionId the collection id to add
     * @param bookId       the book id to add
     * @param request      the body to be saved
     * @return a {@link ResponseEntity} with the response registered
     */
    @PostMapping(COLLECTIONS_BOOKS_PATH)
    public ResponseEntity<CollectionsBookResponse> register(@Min(1L) @PathVariable("collectionId") long collectionId,
                                                            @Min(1L) @PathVariable("bookId") long bookId,
                                                            @Valid @RequestBody CollectionsBookRequest request)
            throws RegisterNotValidException {

        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(collectionId, bookId, request));
    }

    /**
     * Update an existing collections-books by the composite pk.
     *
     * @param collectionId the collection id to search
     * @param bookId       the book id to search
     * @param request      the body to be updated
     * @return a {@link ResponseEntity} with the response updated
     */
    @PutMapping(COLLECTIONS_BOOKS_PATH)
    public ResponseEntity<CollectionsBookResponse> update(@Min(1L) @PathVariable("collectionId") long collectionId,
                                                          @Min(1L) @PathVariable("bookId") long bookId,
                                                          @Valid @RequestBody CollectionsBookRequest request) {
        return ResponseEntity.of(service.update(collectionId, bookId, request));
    }

    /**
     * Delete an existing collections-books by the composite pk.
     *
     * @param collectionId the collection id
     * @param bookId       the book id
     * @return a {@link ResponseEntity} with the 200 or 404 code
     */
    @DeleteMapping(COLLECTIONS_BOOKS_PATH)
    public ResponseEntity<CollectionsBookResponse> delete(@Min(1L) @PathVariable("collectionId") long collectionId,
                                                          @Min(1L) @PathVariable("bookId") long bookId) {
        return service.delete(collectionId, bookId) ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}
