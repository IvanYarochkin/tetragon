package com.yarachkin.tetragon.factory.impl;

import com.yarachkin.tetragon.entity.Point;
import com.yarachkin.tetragon.entity.Tetragon;
import com.yarachkin.tetragon.exception.CommonTetragonException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class FactoryImplTest {
    private Point point;
    private Tetragon tetragon;

    @BeforeMethod
    public void setUp() throws CommonTetragonException {
        point = new Point();
        tetragon = new Tetragon();
    }

    @Test
    public void factoryMethodPointTest() throws CommonTetragonException {
        FactoryImpl factory = new FactoryImpl();
        assertEquals(factory.factoryMethod("point"), point);
    }

    @Test
    public void factoryMethodTetragonTest() throws CommonTetragonException {
        FactoryImpl factory = new FactoryImpl();
        assertEquals(factory.factoryMethod("tetragon"), tetragon);
    }

    @Test(expectedExceptions = CommonTetragonException.class)
    public void factoryMethodHavingIncorrectParameterTest() throws Exception {
        FactoryImpl factory = new FactoryImpl();
        factory.factoryMethod("test");
    }
}