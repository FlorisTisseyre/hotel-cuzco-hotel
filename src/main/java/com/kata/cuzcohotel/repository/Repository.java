package com.kata.cuzcohotel.repository;

import java.util.List;

public interface Repository<T> {
    List<T> getAll();
    void save(T element);
    void delete(T element);
}
