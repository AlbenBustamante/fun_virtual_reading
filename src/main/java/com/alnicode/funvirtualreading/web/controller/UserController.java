package com.alnicode.funvirtualreading.web.controller;

import com.alnicode.funvirtualreading.domain.dto.BookResponse;
import com.alnicode.funvirtualreading.domain.dto.NationalityResponse;
import com.alnicode.funvirtualreading.domain.dto.UserRequest;
import com.alnicode.funvirtualreading.domain.dto.UserResponse;
import com.alnicode.funvirtualreading.domain.service.IBookService;
import com.alnicode.funvirtualreading.domain.service.ICrudService;
import com.alnicode.funvirtualreading.domain.service.INationalityService;
import com.alnicode.funvirtualreading.domain.service.IUserService;
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


import static com.alnicode.funvirtualreading.constants.DateFormatConstants.LIKES_PATH;

/**
 * The user rest controller.
 *
 * @author Alben Bustamante
 * @version 1.0
 * @since 1.0
 */
@Validated
@RestController
@RequestMapping("/users")
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
     * Get all the books published by the same person id.
     *
     * @param personId the id to search
     * @return a {@link ResponseEntity} with the books list
     */
    @GetMapping("/{id}/books")
    public ResponseEntity<List<BookResponse>> getAllPublishedBooks(@Min(1L) @PathVariable("id") long personId) {
        return ResponseEntity.of(this.bookService.getByAuthorId(personId));
    }

    /**
     * Get the person's nationality.
     *
     * @param personId the id to search
     * @return a {@link ResponseEntity} with the nationality found
     */
    @GetMapping("/{id}/nationality")
    public ResponseEntity<NationalityResponse> getNationality(@Min(1L) @PathVariable("id") long personId) {
        return ResponseEntity.of(this.nationalityService.getByAuthorId(personId));
    }

    /**
     * Get a person response by the email.
     *
     * @param email the email to search
     * @return a {@link ResponseEntity} with the response found
     */
    @GetMapping("/email/{email}")
    public ResponseEntity<UserResponse> getByEmail(@NotBlank @Email @PathVariable("email") String email) {
        return ResponseEntity.of(this.service.getByEmail(email));
    }

    /**
     * Get all the persons with the same nationality id.
     *
     * @param nationalityId the id to search
     * @return a {@link ResponseEntity} with the persons list
     */
    @GetMapping("/nationality/{id}")
    public ResponseEntity<List<UserResponse>> getAllByNationality(@Min(1L) @PathVariable("id") long nationalityId) {
        return ResponseEntity.of(this.service.getByNationality(nationalityId));
    }

    /**
     * Add a book to the person's likes list
     *
     * @param userId the user id to add
     * @param bookId   the book id to add
     * @return a {@link ResponseEntity} with the person response found
     */
    @PostMapping(LIKES_PATH)
    public ResponseEntity<UserResponse> addLike(
            @Min(1L) @PathVariable("id") long userId,
            @Min(1L) @PathVariable("bookId") long bookId) {
        return ResponseEntity.of(this.service.addLike(userId, bookId));
    }

    /**
     * Remove a book to the person's likes list
     *
     * @param userId the user id to remove
     * @param bookId   the book id to remove
     * @return a {@link ResponseEntity} with the person response found
     */
    @DeleteMapping(LIKES_PATH)
    public ResponseEntity<UserResponse> removeLike(
            @Min(1L) @PathVariable("id") long userId,
            @Min(1L) @PathVariable("bookId") long bookId) {
        return ResponseEntity.of(this.service.removeLike(userId, bookId));
    }

}
