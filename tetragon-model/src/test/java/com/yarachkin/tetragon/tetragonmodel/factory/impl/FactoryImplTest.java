package com.yarachkin.tetragon.tetragonmodel.factory.impl;

import com.yarachkin.tetragon.tetragonmodel.entity.Point;
import com.yarachkin.tetragon.tetragonmodel.entity.Tetragon;
import com.yarachkin.tetragon.tetragonmodel.exception.ModelTetragonException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class FactoryImplTest {

    private Point point;
    private Tetragon tetragon;

    @BeforeMethod
    public void setUp() throws ModelTetragonException {
        point = new Point();
        tetragon = new Tetragon();
    }

    @Test
    public void factoryMethodPointTest() throws ModelTetragonException {
        FactoryImpl factory = new FactoryImpl();
        assertEquals(factory.factoryMethod("point"), point);
    }

    @Test
    public void factoryMethodTetragonTest() throws ModelTetragonException {
        FactoryImpl factory = new FactoryImpl();
        assertEquals(factory.factoryMethod("tetragon"), tetragon);
    }

    @Test(expectedExceptions = ModelTetragonException.class)
    public void factoryMethodHavingIncorrectParameterTest() throws Exception {
        FactoryImpl factory = new FactoryImpl();
        factory.factoryMethod("test");
    }

}