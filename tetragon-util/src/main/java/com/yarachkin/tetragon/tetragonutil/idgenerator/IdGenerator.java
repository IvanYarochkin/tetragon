package com.yarachkin.tetragon.tetragonutil.idgenerator;

public class IdGenerator {
    private static long id = 0;

    private IdGenerator(){

    }

    public static long generateId() {
        id++;
        return id;
    }
}
