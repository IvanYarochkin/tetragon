package com.yarachkin.tetragon.tetragonmodel.entity;

public class Tetragon {
    private long id;
    private Point firstPoint;
    private Point secondPoint;
    private Point thirdPoint;
    private Point fourthPoint;

    public Tetragon(long id, Point firstPoint, Point secondPoint, Point thirdPoint, Point fourthPoint) {
        this.id = id;
        this.firstPoint = firstPoint;
        this.secondPoint = secondPoint;
        this.thirdPoint = thirdPoint;
        this.fourthPoint = fourthPoint;
    }

    public long getId() {
        return id;
    }

    public Point getFirstPoint() {
        return firstPoint;
    }

    public void setFirstPoint(Point firstPoint) {
        this.firstPoint = firstPoint;
    }

    public Point getSecondPoint() {
        return secondPoint;
    }

    public void setSecondPoint(Point secondPoint) {
        this.secondPoint = secondPoint;
    }

    public Point getThirdPoint() {
        return thirdPoint;
    }

    public void setThirdPoint(Point thirdPoint) {
        this.thirdPoint = thirdPoint;
    }

    public Point getFourthPoint() {
        return fourthPoint;
    }

    public void setFourthPoint(Point fourthPoint) {
        this.fourthPoint = fourthPoint;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if ((object == null) || (getClass() != object.getClass())) {
            return false;
        }

        Tetragon tetragon = (Tetragon) object;

        return (tetragon.getFirstPoint().equals(this.getFirstPoint())) && (tetragon.getSecondPoint().equals(this.getSecondPoint())) &&
                (tetragon.getThirdPoint().equals(this.getThirdPoint()) && (tetragon.getFourthPoint().equals(this.getFourthPoint())));
    }

    @Override
    public int hashCode() {
        int result = 1;
        int prime = 31;

        result = result * prime + (int) id;
        result = result * prime + firstPoint.hashCode();
        result = result * prime + secondPoint.hashCode();
        result = result * prime + thirdPoint.hashCode();
        result = result * prime + fourthPoint.hashCode();

        return result;
    }

    @Override
    public String toString() {
        return firstPoint.getX() + " " + firstPoint.getY() + " " + secondPoint.getX() + " " + secondPoint.getY() + " " +
                "" + thirdPoint.getX() + " " + thirdPoint.getY() + " " + fourthPoint.getX() + " " + fourthPoint.getY();
    }
}
