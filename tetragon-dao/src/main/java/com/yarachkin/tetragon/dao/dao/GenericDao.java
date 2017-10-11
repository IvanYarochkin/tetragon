package com.yarachkin.tetragon.dao.dao;

import com.yarachkin.tetragon.dao.exception.DaoTetragonException;

import java.util.List;
import java.util.Optional;

public interface GenericDao<E> {

    void create(E entity) throws DaoTetragonException;

    Optional<E> findById(long id);

    boolean update(long id, E entity) throws DaoTetragonException;

    boolean delete(long id) throws DaoTetragonException;

    List<E> findAll();
}
