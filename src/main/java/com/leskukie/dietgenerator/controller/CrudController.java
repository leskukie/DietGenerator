package com.leskukie.dietgenerator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

public abstract class CrudController<T, R extends JpaRepository<T, Long>> {

    @Autowired
    private R repository;

    @GetMapping
    public List<T> getAll() {
        return this.repository.findAll();
    }

    @GetMapping("/{id}")
    public T getById(@PathVariable("id") long id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public T create(@RequestBody T t) {
        return this.repository.save(t);
    }

    @DeleteMapping
    public void deleteAll() {
        this.repository.deleteAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        this.repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        this.repository.deleteById(id);
    }
}
