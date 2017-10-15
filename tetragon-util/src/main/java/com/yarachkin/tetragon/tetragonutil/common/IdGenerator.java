package com.yarachkin.tetragon.tetragonutil.common;

public final class IdGenerator {
    private static long tetragonId = 0;
    private static long pointId = 0;

    private static boolean isTest = false;

    private IdGenerator() {

    }

    public static void setIsTest(boolean isTest) {
        IdGenerator.isTest = isTest;
    }

    public static long generateTetragonId() {
        return isTest ? 1L : ++tetragonId;
    }

    public static long generatePointId() {
        return isTest ? 1L : ++pointId;
    }
}
