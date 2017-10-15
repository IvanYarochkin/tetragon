package com.yarachkin.tetragon.tetragonmodel.entity;

public class Tetragon extends AbstractEntity implements Cloneable {
    private Point first;
    private Point second;
    private Point third;
    private Point fourth;

    public Tetragon() {

    }

    public Tetragon(Point first, Point second, Point third, Point fourth) {
        this.first = first;
        this.second = second;
        this.third = third;
        this.fourth = fourth;
    }

    public Tetragon(long id, Point first, Point second, Point third, Point fourth) {
        super(id);
        this.first = first;
        this.second = second;
        this.third = third;
        this.fourth = fourth;
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

        if (first != null ? !first.equals(tetragon.first) : tetragon.first != null) {
            return false;
        }
        if (second != null ? !second.equals(tetragon.second) : tetragon.second != null) {
            return false;
        }
        if (third != null ? !third.equals(tetragon.third) : tetragon.third != null) {
            return false;
        }
        return fourth != null ? fourth.equals(tetragon.fourth) : tetragon.fourth == null;
    }

    @Override
    public int hashCode() {
        int result = 1;
        int prime = 31;

        result = result * prime + (int) getId();
        result = result * prime + (first != null ? first.hashCode() : 0);
        result = result * prime + (second != null ? second.hashCode() : 0);
        result = result * prime + (third != null ? third.hashCode() : 0);
        result = result * prime + (fourth != null ? fourth.hashCode() : 0);

        return result;
    }

    @Override
    public Tetragon clone() throws CloneNotSupportedException {
        Tetragon cloneTetragon = (Tetragon) super.clone();

        cloneTetragon.first = first.clone();
        cloneTetragon.second = second.clone();
        cloneTetragon.third = third.clone();
        cloneTetragon.fourth = fourth.clone();

        return cloneTetragon;
    }

    @Override
    public String toString() {
        return first.getX() + " " + first.getY() + " " + second.getX() + " " + second.getY() + " " +
                "" + third.getX() + " " + third.getY() + " " + fourth.getX() + " " + fourth.getY();
    }
}
