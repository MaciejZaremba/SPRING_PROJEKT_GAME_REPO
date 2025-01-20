package com.pjait.Games_API.services;

import com.pjait.Games_Data.exceptions.EntityNotFound;
import org.springframework.data.jpa.repository.JpaRepository;

import java.lang.reflect.Field;
import java.util.List;

public abstract class BaseService<T> {
    protected final JpaRepository<T, Long> repository;

    protected BaseService(JpaRepository<T, Long> repository) {
        this.repository = repository;
    }

    protected List<T> findAllEntities() {
        List<T> list = repository.findAll();
        return list;
    }

    protected T findById(Long id) {
        return repository.findById(id).get();
    }

    protected void deleteById(Long id) {
        if (repository.findById(id).isPresent()) {
            repository.deleteById(id);
        }
    }

    protected void add(T entity) {
        repository.save(entity);
    }

    protected void update(T entity, Long id) {
        T oldEntity = null;
        if(repository.findById(id).isPresent()) {
            oldEntity = repository.findById(id).get();
        } else {
            throw new EntityNotFound();
        }
        try {
            Field idField = entity.getClass().getDeclaredField("id");
            idField.setAccessible(true);
            idField.set(entity, id);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        repository.save(entity);
    }

}
