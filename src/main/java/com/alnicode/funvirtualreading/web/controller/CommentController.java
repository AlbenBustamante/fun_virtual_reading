package com.alnicode.funvirtualreading.web.controller;

import com.alnicode.funvirtualreading.domain.dto.BookResponse;
import com.alnicode.funvirtualreading.domain.dto.CommentRequest;
import com.alnicode.funvirtualreading.domain.dto.CommentResponse;
import com.alnicode.funvirtualreading.domain.service.IBookService;
import com.alnicode.funvirtualreading.domain.service.ICommentService;
import com.alnicode.funvirtualreading.domain.service.ICrudService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;
import java.util.List;
import javax.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import static com.alnicode.funvirtualreading.constants.SwaggerConstants.API_KEY_NAME;

/**
 * The comment rest controller.
 *
 * @author Alben Bustamante
 * @version 1.0
 * @since 1.0
 */
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

    /**
     * Get the comment's book.
     *
     * @param commentId the id to search
     * @return a {@link ResponseEntity} with the book found
     */
    @GetMapping("/{id}/book")
    @ApiOperation(value = "Get the comment source book", authorizations = {@Authorization(API_KEY_NAME)})
    @ApiResponses({
            @ApiResponse(code = 200, message = "Book found!"),
            @ApiResponse(code = 404, message = "Comment not found")})
    public ResponseEntity<BookResponse> getBook(@Min(1L) @PathVariable("id") long commentId) {
        return ResponseEntity.of(this.bookService.getByComment(commentId));
    }

    /**
     * Get all the person's comments.
     *
     * @param userId the id to search
     * @return a {@link ResponseEntity} with the comments list
     */
    @GetMapping("/user/{id}")
    @ApiOperation(value = "Get all the comments made by the same user", authorizations = {@Authorization(API_KEY_NAME)})
    @ApiResponses({
            @ApiResponse(code = 200, message = "Comments found!"),
            @ApiResponse(code = 404, message = "User not found")})
    public ResponseEntity<List<CommentResponse>> getAllByUser(@Min(1L) @PathVariable("id") long userId) {
        return ResponseEntity.of(this.service.getByUser(userId));
    }

}
