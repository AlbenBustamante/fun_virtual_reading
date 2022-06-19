package com.alnicode.funvirtualreading.web.rest;

import com.alnicode.funvirtualreading.domain.dto.BookResponse;
import com.alnicode.funvirtualreading.domain.dto.TagRequest;
import com.alnicode.funvirtualreading.domain.dto.TagResponse;
import com.alnicode.funvirtualreading.domain.service.IBookService;
import com.alnicode.funvirtualreading.domain.service.ICrudService;
import com.alnicode.funvirtualreading.domain.service.ITagService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;
import java.util.List;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import static com.alnicode.funvirtualreading.constants.SwaggerConstants.API_KEY_NAME;

/**
 * The tag rest controller.
 *
 * @author Alben Bustamante
 * @since 1.0
 * @version 1.0
 */
@RestController
@RequestMapping("/tags")
@Validated
public class TagController extends CrudController<TagRequest, TagResponse> {
    private final ITagService service;
    private final IBookService bookService;

    @Autowired
    public TagController(ITagService service, IBookService bookService) {
        this.service = service;
        this.bookService = bookService;
    }

    @Override
    protected ICrudService<TagRequest, TagResponse> service() {
        return service;
    }

    /**
     * Get a tag by name.
     *
     * @param name the name to search
     * @return a {@link ResponseEntity} with the {@link TagResponse} found
     */
    @GetMapping("/name/{name}")
    @ApiOperation(value = "Get a tag by the name", authorizations = {@Authorization(API_KEY_NAME)})
    @ApiResponses({
            @ApiResponse(code = 200, message = "Tag found!"),
            @ApiResponse(code = 404, message = "Tag not found")})
    public ResponseEntity<TagResponse> getByName(@NotBlank @PathVariable("name") String name) {
        return ResponseEntity.of(service.getByName(name));
    }

    /**
     * Get all the books with the same tag ID.
     *
     * @param tagId the id to search
     * @return a {@link ResponseEntity} with the books found
     */
    @ApiOperation(value = "Get all the tag's books", authorizations = {@Authorization(API_KEY_NAME)})
    @ApiResponses({
            @ApiResponse(code = 200, message = "Books found!"),
            @ApiResponse(code = 404, message = "Tag not found")})
    @GetMapping("/{id}/books")
    public ResponseEntity<List<BookResponse>> getBooks(@NotNull @Min(1L) @PathVariable("id") long tagId) {
        return ResponseEntity.of(bookService.getByTagId(tagId));
    }

}
