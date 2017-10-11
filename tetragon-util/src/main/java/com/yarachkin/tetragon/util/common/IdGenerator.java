package com.yarachkin.tetragon.util.common;

public class IdGenerator {
    private static long id = 0;

    private IdGenerator() {

    }

    public static long generateId() {
        return ++id;
    }
}
