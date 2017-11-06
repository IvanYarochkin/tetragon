package com.yarachkin.tetragon.dao.impl;

import com.yarachkin.tetragon.cache.TetragonCache;
import com.yarachkin.tetragon.dao.TetragonDao;
import com.yarachkin.tetragon.dao.exception.DaoTetragonException;
import com.yarachkin.tetragon.entity.Tetragon;
import com.yarachkin.tetragon.exception.IOTetragonException;

import java.util.List;
import java.util.Optional;

public class TetragonDaoImpl implements TetragonDao {

    @Override
    public List<Tetragon> findAll() {
        return TetragonCache.getInstance().getCache();
    }

    public void create(Tetragon tetragon) throws DaoTetragonException {
        try {
            TetragonCache.getInstance().add(tetragon);
            TetragonCache.getInstance().flush();
        } catch (IOTetragonException e) {
            throw new DaoTetragonException(e);
        }
    }

    public Optional<Tetragon> findById(long id) {

        return TetragonCache.getInstance().getCache().stream()
                .filter(tetragon -> tetragon.getId() == id)
                .findFirst();
    }

    public boolean update(long id, Tetragon tetragon) throws DaoTetragonException {
        try {
            if ( TetragonCache.getInstance().update(id, tetragon) ) {
                TetragonCache.getInstance().flush();
                return true;
            }

            return false;
        } catch (IOTetragonException e) {
            throw new DaoTetragonException(e);
        }
    }

    public boolean delete(long id) throws DaoTetragonException {
        try {
            if ( TetragonCache.getInstance().remove(id) ) {
                TetragonCache.getInstance().flush();
                return true;
            }

            return false;
        } catch (IOTetragonException e) {
            throw new DaoTetragonException(e.getMessage(), e);
        }
    }
}