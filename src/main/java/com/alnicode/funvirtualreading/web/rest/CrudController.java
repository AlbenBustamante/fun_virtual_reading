package com.alnicode.funvirtualreading.web.rest;

import com.alnicode.funvirtualreading.domain.service.ICrudService;
import com.alnicode.funvirtualreading.exception.RegisterNotValidException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


import static com.alnicode.funvirtualreading.constants.SwaggerConstants.API_KEY_NAME;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.of;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;

/**
 * This is an abstract and generic controller with the CRUD methods.
 * <p>Use the {@code @Validation} annotation if you'll use more methods to validate.</p>
 *
 * @param <Request>  the request object
 * @param <Response> the response object
 */
@Validated
public abstract class CrudController<Request, Response> {

    /**
     * Set the service to be used.
     *
     * @return the service used.
     */
    protected abstract ICrudService<Request, Response> service();

    /**
     * Get all the registered elements.
     *
     * @return a {@link ResponseEntity} with the responses list.
     */
    @GetMapping
    @ApiOperation(value = "Get a list with all the registered items", authorizations = {@Authorization(API_KEY_NAME)})
    @ApiResponse(code = 200, message = "OK")
    public ResponseEntity<List<Response>> getAll() {
        return ok(this.service().getAll());
    }

    /**
     * Get an element by the id.
     *
     * @param id the id to search.
     * @return a {@link ResponseEntity} with the response found.
     */
    @GetMapping("/{id}")
    @ApiOperation(value = "Get an item by the ID", authorizations = {@Authorization(API_KEY_NAME)})
    @ApiResponses({
            @ApiResponse(code = 200, message = "Item found"),
            @ApiResponse(code = 404, message = "Item not found")})
    public ResponseEntity<Response> get(@Min(1L) @PathVariable("id") long id) {
        return of(this.service().get(id));
    }


    /**
     * Create and register a new element.
     *
     * @param request the request with the data to be registered.
     * @return a {@link ResponseEntity} with the response registered.
     */
    @PostMapping
    @ApiOperation(value = "Create and register a new item", authorizations = {@Authorization(API_KEY_NAME)})
    @ApiResponses({
            @ApiResponse(code = 201, message = "Item registered successfully!"),
            @ApiResponse(code = 400, message = "Something went wrong")})
    public ResponseEntity<Response> register(@Valid @RequestBody Request request) throws RegisterNotValidException {
        return status(CREATED).body(this.service().create(request));
    }

    /**
     * Update an element by the id.
     *
     * @param id      the id to search and be updated.
     * @param request the request with the data to be used.
     * @return a {@link ResponseEntity} with the response updated.
     */
    @PutMapping("/{id}")
    @ApiOperation(value = "Update an existing item by the ID", authorizations = {@Authorization(API_KEY_NAME)})
    @ApiResponses({
            @ApiResponse(code = 200, message = "Item updated successfully!"),
            @ApiResponse(code = 400, message = "Something went wrong"),
            @ApiResponse(code = 404, message = "Item not found")})
    public ResponseEntity<Response> update(@Min(1L) @PathVariable("id") long id,
                                           @Valid @RequestBody Request request) {
        return of(this.service().update(id, request));
    }

    /**
     * Delete an element by the id.
     *
     * @param id the id to search and be deleted.
     * @return a {@link ResponseEntity} with the 200 or 404 code.
     */
    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete an existing item by the ID", authorizations = {@Authorization(API_KEY_NAME)})
    @ApiResponses({
            @ApiResponse(code = 200, message = "Item deleted successfully!"),
            @ApiResponse(code = 404, message = "Item not found")})
    public ResponseEntity<Response> delete(@Min(1L) @PathVariable("id") long id) {
        return this.service().delete(id) ? ok().build() : notFound().build();
    }
}
