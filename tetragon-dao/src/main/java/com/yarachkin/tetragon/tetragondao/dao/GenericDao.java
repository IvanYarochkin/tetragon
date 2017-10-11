package com.yarachkin.tetragon.tetragondao.dao;

import com.yarachkin.tetragon.tetragondao.exception.DaoTetragonException;
import com.yarachkin.tetragon.tetragonmodel.entity.Tetragon;

import java.util.List;

public interface GenericDao {

    void create(Tetragon tetragon) throws DaoTetragonException;

    Tetragon find(long id) throws DaoTetragonException;

    boolean update(long id, Tetragon tetragon) throws DaoTetragonException;

    boolean delete(long id) throws DaoTetragonException;

    List<Tetragon> findAll();
}
