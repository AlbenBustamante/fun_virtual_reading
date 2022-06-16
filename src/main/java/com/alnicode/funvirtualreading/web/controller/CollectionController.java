package com.alnicode.funvirtualreading.web.controller;

import com.alnicode.funvirtualreading.domain.dto.CollectionRequest;
import com.alnicode.funvirtualreading.domain.dto.CollectionResponse;
import com.alnicode.funvirtualreading.domain.service.ICollectionService;
import com.alnicode.funvirtualreading.domain.service.ICrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The collection rest controller.
 *
 * @author Alben Bustamante
 * @version 1.0
 * @since 1.0
 */
@RestController
@RequestMapping("/collections")
public class CollectionController extends CrudController<CollectionRequest, CollectionResponse> {
    @Autowired
    private ICollectionService service;

    @Override
    protected ICrudService<CollectionRequest, CollectionResponse> service() {
        return this.service;
    }

}
