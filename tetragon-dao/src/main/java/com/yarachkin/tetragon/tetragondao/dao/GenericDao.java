package com.yarachkin.tetragon.tetragondao.dao;

import com.yarachkin.tetragon.tetragondao.exception.DaoTetragonException;

import java.util.List;
import java.util.Optional;

public interface GenericDao<E> {

    List<E> findAll();

    void create(E entity) throws DaoTetragonException;

    Optional<E> findById(long id);

    boolean update(long id, E entity) throws DaoTetragonException;

    boolean delete(long id) throws DaoTetragonException;
}
