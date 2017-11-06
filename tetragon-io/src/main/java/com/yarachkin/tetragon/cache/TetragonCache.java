package com.yarachkin.tetragon.cache;

import com.yarachkin.tetragon.entity.Tetragon;
import com.yarachkin.tetragon.exception.IOTetragonException;
import com.yarachkin.tetragon.reader.TetragonFileReader;
import com.yarachkin.tetragon.util.LineParser;
import com.yarachkin.tetragon.writer.TetragonFileWriter;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TetragonCache {

    private static final Logger LOGGER = LogManager.getRootLogger();

    private List<Tetragon> cache;

    private TetragonCache() throws IOTetragonException {
        TetragonFileReader reader = TetragonFileReader.getInstance();
        cache = LineParser.parse(reader.readFromFile(), "\\s");
    }

    private static class SingletonHolder {
        private static final TetragonCache INSTANCE;

        static {
            try {
                INSTANCE = new TetragonCache();
            } catch (IOTetragonException e) {
                throw new ExceptionInInitializerError(e);
            }
        }

    }

    public static TetragonCache getInstance() {
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

    public void refillCache() throws IOTetragonException {
        TetragonFileReader reader = TetragonFileReader.getInstance();
        cache = LineParser.parse(reader.readFromFile(), "\\s");
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

    public void flush() throws IOTetragonException {
        TetragonFileWriter.getInstance().write(getCache());
    }
}
