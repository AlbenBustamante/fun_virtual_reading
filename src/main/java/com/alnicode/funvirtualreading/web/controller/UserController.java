package com.alnicode.funvirtualreading.web.controller;

import com.alnicode.funvirtualreading.domain.dto.BookResponse;
import com.alnicode.funvirtualreading.domain.dto.NationalityResponse;
import com.alnicode.funvirtualreading.domain.dto.UserRequest;
import com.alnicode.funvirtualreading.domain.dto.UserResponse;
import com.alnicode.funvirtualreading.domain.service.IBookService;
import com.alnicode.funvirtualreading.domain.service.ICrudService;
import com.alnicode.funvirtualreading.domain.service.INationalityService;
import com.alnicode.funvirtualreading.domain.service.IUserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;
import java.util.List;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import static com.alnicode.funvirtualreading.constants.SwaggerConstants.API_KEY_NAME;
import static com.alnicode.funvirtualreading.constants.UserConstants.LIKES_PATH;
import static com.alnicode.funvirtualreading.constants.UserConstants.MAIN_PATH;

/**
 * The user rest controller.
 *
 * @author Alben Bustamante
 * @version 1.0
 * @since 1.0
 */
@Validated
@RestController
@RequestMapping(MAIN_PATH)
public class UserController extends CrudController<UserRequest, UserResponse> {
    @Autowired
    private IUserService service;

    @Autowired
    private IBookService bookService;

    @Autowired
    private INationalityService nationalityService;

    @Override
    protected ICrudService<UserRequest, UserResponse> service() {
        return this.service;
    }

    /**
     * Get all the books published by the same user id.
     *
     * @param userId the id to search
     * @return a {@link ResponseEntity} with the books list
     */
    @GetMapping("/{id}/books")
    @ApiOperation(value = "Get all the books published by the same user", authorizations = {@Authorization(API_KEY_NAME)})
    @ApiResponses({
            @ApiResponse(code = 200, message = "Books found!"),
            @ApiResponse(code = 404, message = "User not found")})
    public ResponseEntity<List<BookResponse>> getAllPublishedBooks(@Min(1L) @PathVariable("id") long userId) {
        return ResponseEntity.of(this.bookService.getByAuthorId(userId));
    }

    /**
     * Get the user's nationality.
     *
     * @param userId the id to search
     * @return a {@link ResponseEntity} with the nationality found
     */
    @GetMapping("/{id}/nationality")
    @ApiOperation(value = "Get the user's nationality", authorizations = {@Authorization(API_KEY_NAME)})
    @ApiResponses({
            @ApiResponse(code = 200, message = "Nationality found!"),
            @ApiResponse(code = 404, message = "User not found")})
    public ResponseEntity<NationalityResponse> getNationality(@Min(1L) @PathVariable("id") long userId) {
        return ResponseEntity.of(this.nationalityService.getByAuthorId(userId));
    }

    /**
     * Get a user response by the email.
     *
     * @param email the email to search
     * @return a {@link ResponseEntity} with the response found
     */
    @GetMapping("/email/{email}")
    @ApiOperation(value = "Discover a user by the email", authorizations = {@Authorization(API_KEY_NAME)})
    @ApiResponses({
            @ApiResponse(code = 200, message = "User found!"),
            @ApiResponse(code = 404, message = "Email not found")})
    public ResponseEntity<UserResponse> getByEmail(@NotBlank @Email @PathVariable("email") String email) {
        return ResponseEntity.of(this.service.getByEmail(email));
    }

    /**
     * Get all the users with the same nationality id.
     *
     * @param nationalityId the id to search
     * @return a {@link ResponseEntity} with the users list
     */
    @GetMapping("/nationality/{id}")
    @ApiOperation(value = "Show all the users with the same nationality", authorizations = {@Authorization(API_KEY_NAME)})
    @ApiResponses({
            @ApiResponse(code = 200, message = "Users found!"),
            @ApiResponse(code = 404, message = "Nationality not found")})
    public ResponseEntity<List<UserResponse>> getAllByNationality(@Min(1L) @PathVariable("id") long nationalityId) {
        return ResponseEntity.of(this.service.getByNationality(nationalityId));
    }

    /**
     * Add a book to the user's likes list
     *
     * @param userId the user id to add
     * @param bookId the book id to add
     * @return a {@link ResponseEntity} with the person response found
     */
    @PostMapping(LIKES_PATH)
    @ApiOperation(value = "Add a book to the likes collection", authorizations = {@Authorization(API_KEY_NAME)})
    @ApiResponses({
            @ApiResponse(code = 200, message = "Book added successfully!"),
            @ApiResponse(code = 404, message = "Book or user not found")})
    public ResponseEntity<UserResponse> addLike(@Min(1L) @PathVariable("id") long userId,
                                                @Min(1L) @PathVariable("bookId") long bookId) {
        return ResponseEntity.of(this.service.addLike(userId, bookId));
    }

    /**
     * Remove a book to the user's likes list
     *
     * @param userId the user id to remove
     * @param bookId the book id to remove
     * @return a {@link ResponseEntity} with the person response found
     */
    @DeleteMapping(LIKES_PATH)
    @ApiOperation(value = "Remove a book to the likes collection", authorizations = {@Authorization(API_KEY_NAME)})
    @ApiResponses({
            @ApiResponse(code = 200, message = "Book removed successfully!"),
            @ApiResponse(code = 404, message = "Book or user not found")})
    public ResponseEntity<UserResponse> removeLike(@Min(1L) @PathVariable("id") long userId,
                                                   @Min(1L) @PathVariable("bookId") long bookId) {
        return ResponseEntity.of(this.service.removeLike(userId, bookId));
    }

}
