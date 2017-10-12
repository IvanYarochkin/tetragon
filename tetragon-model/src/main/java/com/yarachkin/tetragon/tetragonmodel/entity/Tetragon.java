package com.yarachkin.tetragon.tetragonmodel.entity;

public class Tetragon {
    private long id;
    private Point first;
    private Point second;
    private Point third;
    private Point fourth;

    public Tetragon(long id, Point first, Point second, Point third, Point fourth) {
        this.id = id;
        this.first = first;
        this.second = second;
        this.third = third;
        this.fourth = fourth;
    }

    public long getId() {
        return id;
    }

    public Point getFirst() {
        return first;
    }

    public void setFirst(Point first) {
        this.first = first;
    }

    public Point getSecond() {
        return second;
    }

    public void setSecond(Point second) {
        this.second = second;
    }

    public Point getThird() {
        return third;
    }

    public void setThird(Point third) {
        this.third = third;
    }

    public Point getFourth() {
        return fourth;
    }

    public void setFourth(Point fourth) {
        this.fourth = fourth;
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

        return (tetragon.getFirst().equals(this.getFirst())) && (tetragon.getSecond().equals(this.getSecond())) &&
                (tetragon.getThird().equals(this.getThird()) && (tetragon.getFourth().equals(this.getFourth())));
    }

    @Override
    public int hashCode() {
        int result = 1;
        int prime = 31;

        result = result * prime + (int) id;
        result = result * prime + first.hashCode();
        result = result * prime + second.hashCode();
        result = result * prime + third.hashCode();
        result = result * prime + fourth.hashCode();

        return result;
    }

    @Override
    public String toString() {
        return first.getX() + " " + first.getY() + " " + second.getX() + " " + second.getY() + " " +
                "" + third.getX() + " " + third.getY() + " " + fourth.getX() + " " + fourth.getY();
    }
}
