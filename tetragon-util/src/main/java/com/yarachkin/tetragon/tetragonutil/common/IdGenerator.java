package com.yarachkin.tetragon.tetragonutil.common;

public final class IdGenerator {
    private static long tetragonId = 0;
    private static long pointId = 0;

    private IdGenerator() {

    }

    public static long generateTetragonId() {
        return ++tetragonId;
    }

    public static long generatePointId() {
        return ++pointId;
    }
}
