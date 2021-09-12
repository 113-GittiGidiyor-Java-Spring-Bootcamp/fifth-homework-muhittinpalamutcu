package io.github.muhittinpalamutcu.schoolmanagementsystem.service;

import java.util.List;

public interface BaseService<T> {
    List<T> findAll();

    T findById(int id);

    void deleteById(int id);

    T findByName(String name);

    void deleteByName(String name);
}
