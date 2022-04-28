package com.alnicode.funvirtualreading.domain.service;

import java.util.List;
import java.util.Optional;

public interface ICrudService<Request, Response> {
    Response save(Request request);
    List<Response> getAll();
    Optional<Response> get(long id);
    Optional<Response> update(long id, Request request);
    boolean delete(long id);
}
