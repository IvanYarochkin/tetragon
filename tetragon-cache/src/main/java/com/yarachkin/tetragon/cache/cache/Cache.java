package com.yarachkin.tetragon.cache.cache;

import com.yarachkin.tetragon.cache.exception.CacheTetragonException;
import com.yarachkin.tetragon.cache.writer.Writer;
import com.yarachkin.tetragon.model.entity.Tetragon;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public boolean update(long id, Tetragon tetragon) {

        Optional<Tetragon> tetragonOption = cache.stream()
                .filter(tetragon1 -> tetragon.getId() == id)
                .findFirst();
        if (!tetragonOption.isPresent()) {
            return false;
        }

        return true;
    }

    public boolean remove(long id) {

        Optional<Tetragon> tetragonOptional = cache.stream()
                .filter(tetragon -> tetragon.getId() == id)
                .findFirst();
        if (!tetragonOptional.isPresent()) {
            return false;
        }

        return true;
    }

    public void flush() throws CacheTetragonException {
        Writer.getInstance().write();
    }


}
