package com.alnicode.funvirtualreading.web.controller;

import com.alnicode.funvirtualreading.domain.dto.NationalityRequest;
import com.alnicode.funvirtualreading.domain.dto.NationalityResponse;
import com.alnicode.funvirtualreading.domain.service.ICrudService;
import com.alnicode.funvirtualreading.domain.service.INationalityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/nationalities")
public class NationalityController extends CrudController<NationalityRequest, NationalityResponse> {
    @Autowired
    private INationalityService service;

    @Override
    protected ICrudService<NationalityRequest, NationalityResponse> service() {
        return this.service;
    }
}
