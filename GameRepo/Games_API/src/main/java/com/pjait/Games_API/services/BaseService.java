package com.pjait.Games_API.services;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public abstract class BaseService<T> {
    protected final JpaRepository<T, Long> repository;

    protected BaseService(JpaRepository<T, Long> repository) {
        this.repository = repository;
    }

    protected List<T> findAll() {
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
        if(repository.findById(id).isPresent()) {
            repository.save(entity);
        }
    }

}
