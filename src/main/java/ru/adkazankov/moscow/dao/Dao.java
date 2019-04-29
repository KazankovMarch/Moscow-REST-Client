package ru.adkazankov.moscow.dao;

import org.springframework.stereotype.Component;

import java.util.List;

public interface Dao<T> {

    T get(Long id);
    void put(T o);
    T post(T o);
    void delete(Long id);
    List<T> getAll();

}
