package com.yarachkin.tetragon.tetragoncache.cache;

import com.yarachkin.tetragon.tetragoncache.exception.CacheTetragonException;
import com.yarachkin.tetragon.tetragoncache.writer.Writer;
import com.yarachkin.tetragon.tetragonmodel.entity.Tetragon;

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

        cache.forEach(tetragon -> {
            try {
                cloneCache.add(tetragon.clone());
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        });

        return cloneCache;
    }


    public void add(Tetragon tetragon) {
        cache.add(tetragon);
    }

    public boolean update(long id, Tetragon tetragon) {

        Optional<Tetragon> tetragonOption = cache.stream()
                .filter(element -> element.getId() == id)
                .findFirst();
        if (!tetragonOption.isPresent()) {
            return false;
        }
        tetragonOption.get().setFirst(tetragon.getFirst());
        tetragonOption.get().setSecond(tetragon.getSecond());
        tetragonOption.get().setThird(tetragon.getThird());
        tetragonOption.get().setFourth(tetragon.getFourth());

        return true;
    }

    public boolean remove(long id) {

        Optional<Tetragon> tetragonOptional = cache.stream()
                .filter(element -> element.getId() == id)
                .findFirst();
        if (!tetragonOptional.isPresent()) {
            return false;
        }
        cache.remove(tetragonOptional.get());
        return true;
    }

    public void flush() throws CacheTetragonException {
        Writer.getInstance().write();
    }


}
