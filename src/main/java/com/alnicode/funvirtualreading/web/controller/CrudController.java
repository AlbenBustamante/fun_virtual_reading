package com.alnicode.funvirtualreading.web.controller;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.of;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import com.alnicode.funvirtualreading.domain.service.ICrudService;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Validated
public abstract class CrudController<Request, Response> {
    protected abstract ICrudService<Request, Response> service();

    @GetMapping
    public ResponseEntity<List<@Valid Response>> getAll() {
        return ok(this.service().getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<@Valid Response> get(@Min(1L) @PathVariable("id") long id) {
        return of(this.service().get(id));
    }

    @PostMapping
    public ResponseEntity<Response> register(@Valid @RequestBody Request request) {
        return status(CREATED).body(this.service().save(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response> update(@Min(1L) @PathVariable("id") long id,
            @Valid @RequestBody Request request) {
        return of(this.service().update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> delete(@Min(1L) @PathVariable("id") long id) {
        return this.service().delete(id) ? ok().build() : notFound().build();
    }
}
