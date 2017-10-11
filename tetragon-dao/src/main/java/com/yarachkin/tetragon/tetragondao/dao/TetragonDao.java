package com.yarachkin.tetragon.tetragondao.dao;

import com.yarachkin.tetragon.tetragondao.exception.DaoTetragonException;
import com.yarachkin.tetragon.tetragonmodel.entity.Tetragon;

import java.util.List;
import java.util.Optional;

public interface TetragonDao extends GenericDao<Tetragon> {

    @Override
    void create(Tetragon tetragon) throws DaoTetragonException;

    @Override
    Optional<Tetragon> findById(long id);

    @Override
    boolean update(long id, Tetragon tetragon) throws DaoTetragonException;

    @Override
    boolean delete(long id) throws DaoTetragonException;

    @Override
    List findAll();
}
