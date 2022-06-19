package com.alnicode.funvirtualreading.web.rest;

import com.alnicode.funvirtualreading.domain.dto.TagRequest;
import com.alnicode.funvirtualreading.domain.dto.TagResponse;
import com.alnicode.funvirtualreading.domain.service.ICrudService;
import com.alnicode.funvirtualreading.domain.service.ITagService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;
import javax.validation.constraints.NotBlank;
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

    @Autowired
    public TagController(ITagService service) {
        this.service = service;
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

}
