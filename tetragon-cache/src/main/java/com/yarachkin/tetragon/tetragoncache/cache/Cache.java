package com.yarachkin.tetragon.tetragoncache.cache;

import com.yarachkin.tetragon.tetragoncache.exception.CacheTetragonException;
import com.yarachkin.tetragon.tetragoncache.filehelper.FileHelper;
import com.yarachkin.tetragon.tetragoncache.reader.Reader;
import com.yarachkin.tetragon.tetragoncache.writer.Writer;
import com.yarachkin.tetragon.tetragonmodel.entity.Tetragon;
import com.yarachkin.tetragon.tetragonutil.parser.LineParser;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Cache {

    private static final Logger LOGGER = LogManager.getRootLogger();

    private List<Tetragon> cache;

    private Cache() throws CacheTetragonException {
        FileHelper.getInstance();
        Reader reader = Reader.getInstance();
        cache = LineParser.parse(reader.readFromFile(), "\\s");
    }

    private static class SingletonHolder {
        private static final Cache INSTANCE;

        static {
            try {
                INSTANCE = new Cache();
            } catch (CacheTetragonException e) {
                throw new ExceptionInInitializerError(e);
            }
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
                LOGGER.log(Level.ERROR, "Can't create clone object.", e);
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
        if ( !tetragonOption.isPresent() ) {
            return false;
        }
        Tetragon foundTetragon = tetragonOption.get();
        foundTetragon.setFirst(tetragon.getFirst());
        foundTetragon.setSecond(tetragon.getSecond());
        foundTetragon.setThird(tetragon.getThird());
        foundTetragon.setFourth(tetragon.getFourth());

        return true;
    }

    public boolean remove(long id) {

        Optional<Tetragon> tetragonOptional = cache.stream()
                .filter(element -> element.getId() == id)
                .findFirst();
        if ( !tetragonOptional.isPresent() ) {
            return false;
        }
        cache.remove(tetragonOptional.get());
        return true;
    }

    public void flush() throws CacheTetragonException {
        Writer.getInstance().write(getCache());
    }
}
