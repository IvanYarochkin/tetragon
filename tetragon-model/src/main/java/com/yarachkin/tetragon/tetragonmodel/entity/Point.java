package com.yarachkin.tetragon.tetragonmodel.entity;

public class Point extends AbstractEntity implements Cloneable {
    private double x;
    private double y;

    public Point() {

    }

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Point(long id, double x, double y) {
        super(id);
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

        return (point.x == this.x) && (point.y == this.y) && (point.getId() == this.getId());
    }

    @Override
    public int hashCode() {
        int result = 1;
        int prime = 31;

        result = result * prime + (int) getId();
        result = result * prime + (int) x;
        result = result * prime + (int) y;

        return result;
    }

    @Override
    public Point clone() throws CloneNotSupportedException {
        return (Point) super.clone();
    }

    @Override
    public String toString() {
        return "Point{id = " + getId() + ", x = " + x + ", y = " + y + "}";
    }
}
