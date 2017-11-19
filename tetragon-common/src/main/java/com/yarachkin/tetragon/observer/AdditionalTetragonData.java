package com.yarachkin.tetragon.observer;

public class AdditionalTetragonData implements Cloneable {
    private double perimeter;
    private double area;

    public double getPerimeter() {
        return perimeter;
    }

    public void setPerimeter(double perimeter) {
        this.perimeter = perimeter;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    @Override
    public AdditionalTetragonData clone() throws CloneNotSupportedException {
        return (AdditionalTetragonData) super.clone();
    }

    @Override
    public String toString() {
        return "AdditionalTetragonData{" +
                "perimeter=" + perimeter +
                ", area=" + area +
                '}';
    }
}
