package com.yarachkin.tetragon.dao.dao.impl;

import com.yarachkin.tetragon.cache.cache.Cache;
import com.yarachkin.tetragon.cache.exception.CacheTetragonException;
import com.yarachkin.tetragon.dao.dao.TetragonDao;
import com.yarachkin.tetragon.dao.exception.DaoTetragonException;
import com.yarachkin.tetragon.model.entity.Tetragon;

import java.util.List;
import java.util.Optional;

public class TetragonDaoImpl implements TetragonDao {

    public void create(Tetragon tetragon) throws DaoTetragonException {
        try {
            Cache.getInstance().add(tetragon);
            Cache.getInstance().flush();
        } catch (CacheTetragonException e) {
            throw new DaoTetragonException(e);
        }
    }

    public Optional<Tetragon> findById(long id) {

        return Cache.getInstance().getCache().stream()
                .filter(tetragon1 -> tetragon1.getId() == id)
                .findFirst();
    }

    public boolean update(long id, Tetragon tetragon) throws DaoTetragonException {
        try {
            if (Cache.getInstance().update(id, tetragon)) {
                Cache.getInstance().flush();
                return true;
            }

            return false;
        } catch (CacheTetragonException e) {
            throw new DaoTetragonException(e);
        }
    }

    public boolean delete(long id) throws DaoTetragonException {
        try {
            if (Cache.getInstance().remove(id)) {
                Cache.getInstance().flush();
                return true;
            }

            return false;
        } catch (CacheTetragonException e) {
            throw new DaoTetragonException(e.getMessage(), e);
        }
    }

    @Override
    public List<Tetragon> findAll() {
        return Cache.getInstance().getCache();
    }
}
