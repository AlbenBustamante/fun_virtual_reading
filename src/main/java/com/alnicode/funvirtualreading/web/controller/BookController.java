package com.alnicode.funvirtualreading.web.controller;

import com.alnicode.funvirtualreading.domain.dto.BookRequest;
import com.alnicode.funvirtualreading.domain.dto.BookResponse;
import com.alnicode.funvirtualreading.domain.dto.CommentResponse;
import com.alnicode.funvirtualreading.domain.dto.GenreResponse;
import com.alnicode.funvirtualreading.domain.dto.UserResponse;
import com.alnicode.funvirtualreading.domain.service.IBookService;
import com.alnicode.funvirtualreading.domain.service.ICommentService;
import com.alnicode.funvirtualreading.domain.service.ICrudService;
import com.alnicode.funvirtualreading.domain.service.IGenreService;
import com.alnicode.funvirtualreading.domain.service.IUserService;
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
 * The book rest controller.
 *
 * @author Alben Bustamante
 * @version 1.0
 * @since 1.0
 */
@Validated
@RestController
@RequestMapping("/books")
public class BookController extends CrudController<BookRequest, BookResponse> {
    @Autowired
    private IBookService service;

    @Autowired
    private IUserService userService;

    @Autowired
    private IGenreService genreService;

    @Autowired
    private ICommentService commentService;

    @Override
    protected ICrudService<BookRequest, BookResponse> service() {
        return this.service;
    }

    /**
     * Get a list of users who liked a book by the id.
     *
     * @param bookId the id to search
     * @return a {@link ResponseEntity} with the persons list
     */
    @GetMapping("{id}/likes")
    @ApiOperation(value = "Get a list of all the users who have liked this book", authorizations = {@Authorization(API_KEY_NAME)})
    @ApiResponses({
            @ApiResponse(code = 200, message = "Users found!"),
            @ApiResponse(code = 404, message = "Book not found")})
    public ResponseEntity<List<UserResponse>> getUsersWhoLikedIt(@Min(1L) @PathVariable("id") long bookId) {
        return ResponseEntity.of(this.userService.getByBooksLiked(bookId));
    }

    /**
     * Get the book's author.
     *
     * @param bookId the id to search
     * @return a @{@link ResponseEntity} with the person found
     */
    @GetMapping("/{id}/author")
    @ApiOperation(value = "Discover the author of the book", authorizations = {@Authorization(API_KEY_NAME)})
    @ApiResponses({
            @ApiResponse(code = 200, message = "Author found!"),
            @ApiResponse(code = 404, message = "Book not found")})
    public ResponseEntity<UserResponse> getAuthor(@Min(1L) @PathVariable("id") long bookId) {
        return ResponseEntity.of(this.userService.getByPublishedBook(bookId));
    }

    /**
     * Get the book's genre.
     *
     * @param bookId the id to search
     * @return a {@link ResponseEntity} with the genre found
     */
    @GetMapping("/{id}/genre")
    @ApiOperation(value = "Show the book's genre", authorizations = {@Authorization(API_KEY_NAME)})
    @ApiResponses({
            @ApiResponse(code = 200, message = "Genre found!"),
            @ApiResponse(code = 404, message = "Book not found")})
    public ResponseEntity<GenreResponse> getGenre(@Min(1L) @PathVariable("id") long bookId) {
        return ResponseEntity.of(this.genreService.getByBookId(bookId));
    }

    /**
     * Get all the book's comments.
     *
     * @param bookId the id to search
     * @return a {@link ResponseEntity} with the comments list
     */
    @GetMapping("/{id}/comments")
    @ApiOperation(value = "Get all the comments received from the book", authorizations = {@Authorization(API_KEY_NAME)})
    @ApiResponses({
            @ApiResponse(code = 200, message = "Comments found!"),
            @ApiResponse(code = 404, message = "Book not found")})
    public ResponseEntity<List<CommentResponse>> getComments(@Min(1L) @PathVariable("id") long bookId) {
        return ResponseEntity.of(this.commentService.getByBook(bookId));
    }

    /**
     * Get all the books with the same genre id.
     *
     * @param genreId the id to search
     * @return a {@link ResponseEntity} with the books list
     */
    @GetMapping("/genre/{id}")
    @ApiOperation(value = "Get all the books with the same genre", authorizations = {@Authorization(API_KEY_NAME)})
    @ApiResponses({
            @ApiResponse(code = 200, message = "Books found"),
            @ApiResponse(code = 404, message = "Genre not found")})
    public ResponseEntity<List<BookResponse>> getByGenre(@Min(1L) @PathVariable("id") long genreId) {
        return ResponseEntity.of(this.service.getByGenre(genreId));
    }

}
