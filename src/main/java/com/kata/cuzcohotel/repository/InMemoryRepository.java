package com.kata.cuzcohotel.repository;

import java.util.ArrayList;
import java.util.List;

public class InMemoryRepository<T> implements Repository<T> {
    List<T> list = new ArrayList<>();

    @Override
    public List<T> getAll() {
        return list;
    }

    @Override
    public void save(T element) {
        list.add(element);
    }

    @Override
    public void delete(T element) {
        list.remove(element);
    }
}
