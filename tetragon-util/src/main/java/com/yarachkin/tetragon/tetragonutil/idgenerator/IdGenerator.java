package com.yarachkin.tetragon.tetragonutil.idgenerator;

public class IdGenerator {
    private static long id = 0;

    public static long generateId() {
        id++;
        return id;
    }
}
