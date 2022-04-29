package com.alnicode.funvirtualreading.domain.service.impl;

import org.springframework.data.repository.CrudRepository;

public abstract class DeleteService<Entity> {
    protected abstract CrudRepository<Entity, Long> repository();

    public boolean delete(long id) {
        try {
            this.repository().deleteById(id);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}
