package com.yarachkin.tetragon.tetragonmodel.dto;

public class PointDto {
    private String x;
    private String y;

    public PointDto(String x, String y) {
        this.x = x;
        this.y = y;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "PointDto{x=" + x + ", y = " + y + "}";
    }
}
