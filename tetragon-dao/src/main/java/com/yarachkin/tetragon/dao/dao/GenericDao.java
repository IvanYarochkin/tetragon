package com.yarachkin.tetragon.dao.dao;

import com.yarachkin.tetragon.dao.exception.DaoTetragonException;
import com.yarachkin.tetragon.model.entity.Tetragon;

import java.util.List;

public interface GenericDao {

    void create(Tetragon tetragon) throws DaoTetragonException;

    Tetragon find(long id) throws DaoTetragonException;

    boolean update(long id, Tetragon tetragon) throws DaoTetragonException;

    boolean delete(long id) throws DaoTetragonException;

    List<Tetragon> findAll();
}
