package com.yarachkin.tetragon.factory.impl;

import com.yarachkin.tetragon.entity.AbstractEntity;
import com.yarachkin.tetragon.exception.CommonTetragonException;
import com.yarachkin.tetragon.factory.Factory;
import com.yarachkin.tetragon.factory.FactoryEntityType;

import java.util.Arrays;
import java.util.stream.Collectors;

public class FactoryImpl implements Factory {

    @Override
    public AbstractEntity factoryMethod(String objectName) throws CommonTetragonException {
        String objectNameUpperCase = objectName.toUpperCase();

        if ( !Arrays.asList(FactoryEntityType.values()).stream()
                .map(Object::toString)
                .collect(Collectors.toList()).contains(objectNameUpperCase) ) {
            throw new CommonTetragonException("Incorrect value to create object. Value = " + objectName);
        }
        return FactoryEntityType.valueOf(objectNameUpperCase.toUpperCase()).getEntity();
    }
}
