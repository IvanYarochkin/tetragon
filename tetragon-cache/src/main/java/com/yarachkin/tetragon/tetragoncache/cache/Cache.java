package com.yarachkin.tetragon.tetragoncache.cache;

import com.yarachkin.tetragon.tetragoncache.exception.CacheTetragonException;
import com.yarachkin.tetragon.tetragoncache.writer.Writer;
import com.yarachkin.tetragon.tetragonmodel.entity.Tetragon;

import java.util.ArrayList;
import java.util.List;

public class Cache {

    private List<Tetragon> cache;

    private Cache() {
        cache = new ArrayList<>();
    }

    private static class SingletonHolder {
        private static final Cache INSTANCE;

        static {
            INSTANCE = new Cache();
        }
    }

    public static Cache getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public List<Tetragon> getCache() {
        List<Tetragon> cloneCache = new ArrayList<>();
        cloneCache.addAll(cache);
        return cloneCache;
    }

    public void add(Tetragon tetragon) {
        cache.add(tetragon);
    }

    public boolean update(long id, Tetragon tetragon) throws CacheTetragonException {
        for (int i = 0; i < cache.size(); i++) {
            if (cache.get(i).getId() == id) {
                cache.set(i, tetragon);
                return true;
            }
        }
        throw new CacheTetragonException("Id wasn't found");
    }

    public boolean remove(long id) throws CacheTetragonException {
        for (int i = 0; i < cache.size(); i++) {
            if (cache.get(i).getId() == id) {
                cache.remove(id);
                return true;
            }
        }
        throw new CacheTetragonException("Id wasn't found");
    }

    public void flush() throws CacheTetragonException {
        Writer.getInstance().write();
    }


}
