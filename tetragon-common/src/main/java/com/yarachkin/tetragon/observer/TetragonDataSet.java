package com.yarachkin.tetragon.observer;

import com.yarachkin.tetragon.entity.Tetragon;
import com.yarachkin.tetragon.observer.impl.TetragonObserverImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class TetragonDataSet {
    private static final Logger LOGGER = LogManager.getRootLogger();

    private Map<Long, AdditionalTetragonData> additionalTetragonDataSet = new HashMap<>();

    private TetragonDataSet() {
    }

    private static class SingletonHolder {
        private static final TetragonDataSet INSTANCE = new TetragonDataSet();
    }

    public static TetragonDataSet getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public void addAdditionalData(Tetragon tetragon) {
        if ( !additionalTetragonDataSet.containsKey(tetragon.getId()) ) {
            AdditionalTetragonData additionalData = new AdditionalTetragonData();
            TetragonObserver observer = new TetragonObserverImpl(additionalData);
            tetragon.registerTetragonObserver(observer);
            additionalTetragonDataSet.put(tetragon.getId(), additionalData);
        }
    }

    public void removeAdditionalData(long id) {
        additionalTetragonDataSet.remove(id);
    }

    public Map<Long, AdditionalTetragonData> getAdditionalTetragonDataSet() {
        Map<Long, AdditionalTetragonData> additionalDataSetClone = new HashMap<>();
        additionalTetragonDataSet.entrySet().forEach(element -> {
            try {
                additionalDataSetClone.put(element.getKey(), element.getValue().clone());
            } catch (CloneNotSupportedException e) {
                LOGGER.log(Level.ERROR, "Can't create clone object.", e);
            }
        });
        return additionalDataSetClone;
    }
}
