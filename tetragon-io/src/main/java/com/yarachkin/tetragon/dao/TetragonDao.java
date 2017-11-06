package com.yarachkin.tetragon.dao;

import com.yarachkin.tetragon.dao.exception.DaoTetragonException;
import com.yarachkin.tetragon.entity.Tetragon;

import java.util.List;
import java.util.Optional;

public interface TetragonDao extends GenericDao<Tetragon> {

    @Override
    List<Tetragon> findAll();

    @Override
    void create(Tetragon tetragon) throws DaoTetragonException;

    @Override
    Optional<Tetragon> findById(long id);

    @Override
    boolean update(long id, Tetragon tetragon) throws DaoTetragonException;

    @Override
    boolean delete(long id) throws DaoTetragonException;
}