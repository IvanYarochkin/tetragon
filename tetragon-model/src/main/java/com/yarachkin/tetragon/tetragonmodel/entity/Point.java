package com.yarachkin.tetragon.tetragonmodel.entity;

public class Point {
    private double x;
    private double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if ((object == null) || (getClass() != object.getClass())) {
            return false;
        }

        Point point = (Point) object;

        return (point.x == this.x) && (point.y == this.y);
    }

    @Override
    public int hashCode() {
        int result = 1;
        int prime = 31;

        result = result * prime + (int) x;
        result = result * prime + (int) y;

        return result;
    }

    @Override
    public String toString() {
        return "Point{ x = " + x + ", y = " + y + "}";
    }
}
