package com.yarachkin.tetragon.tetragondao.dao;

import com.yarachkin.tetragon.tetragoncache.cache.Cache;
import com.yarachkin.tetragon.tetragoncache.exception.CacheTetragonException;
import com.yarachkin.tetragon.tetragondao.exception.DaoTetragonException;
import com.yarachkin.tetragon.tetragonmodel.entity.Tetragon;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class TetragonDao implements GenericDao {

    public void create(Tetragon tetragon) throws DaoTetragonException {
        try {
            Cache.getInstance().add(tetragon);
            Cache.getInstance().flush();
        } catch (CacheTetragonException e) {
            throw new DaoTetragonException(e.getMessage(), e);
        }
    }

    public Tetragon find(long id) throws DaoTetragonException {
        for (Tetragon tetragon : Cache.getInstance().getCache()) {
            if (tetragon.getId() == id) {
                return tetragon;
            }
        }

        throw new DaoTetragonException("There aren't tetragon having id = " + id);
    }

    public boolean update(long id, Tetragon tetragon) throws DaoTetragonException {
        try {
            Cache.getInstance().update(id, tetragon);
            Cache.getInstance().flush();
            return true;
        } catch (CacheTetragonException e) {
            throw new DaoTetragonException(e.getMessage(), e);
        }
    }

    public boolean delete(long id) throws DaoTetragonException {
        try {
            Cache.getInstance().remove(id);
            Cache.getInstance().flush();
            return true;
        } catch (CacheTetragonException e) {
            throw new DaoTetragonException(e.getMessage(), e);
        }
    }

    @Override
    public List<Tetragon> findAll() {
        return Cache.getInstance().getCache();
    }
}
