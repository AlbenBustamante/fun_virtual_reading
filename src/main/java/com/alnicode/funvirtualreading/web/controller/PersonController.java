package com.alnicode.funvirtualreading.web.controller;

import static com.alnicode.funvirtualreading.util.AppConstants.LIKES_PATH;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import com.alnicode.funvirtualreading.domain.dto.PersonRequest;
import com.alnicode.funvirtualreading.domain.dto.PersonResponse;
import com.alnicode.funvirtualreading.domain.service.ICrudService;
import com.alnicode.funvirtualreading.domain.service.IPersonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/email/{email}")
    public ResponseEntity<PersonResponse> getByEmail(@NotBlank @Email @PathVariable("email") String email) {
        return ResponseEntity.of(this.service.getByEmail(email));
    }

    @GetMapping("nationality/{id}")
    public ResponseEntity<List<PersonResponse>> getByNationality(@Min(1L) @PathVariable("id") long nationalityId) {
        return ResponseEntity.of(this.service.getByNationality(nationalityId));
    }

    @PostMapping(LIKES_PATH)
    public ResponseEntity<PersonResponse> addLike(
            @Min(1L) @PathVariable("id") long personId,
            @Min(1L) @PathVariable("bookId") long bookId) {
        return ResponseEntity.of(this.service.addLike(personId, bookId));
    }

    @DeleteMapping(LIKES_PATH)
    public ResponseEntity<PersonResponse> removeLike(
            @Min(1L) @PathVariable("id") long personId,
            @Min(1L) @PathVariable("bookId") long bookId) {
        return ResponseEntity.of(this.service.removeLike(personId, bookId));
    }
    
}
