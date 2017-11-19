package com.yarachkin.tetragon.entity;

import com.yarachkin.tetragon.observer.TetragonEvent;
import com.yarachkin.tetragon.observer.TetragonObserver;

import java.util.ArrayList;
import java.util.List;

public class Tetragon extends AbstractEntity implements Cloneable {
    private Point first;
    private Point second;
    private Point third;
    private Point fourth;
    private List<TetragonObserver> observers = new ArrayList<>();

    public Tetragon() {
        first = new Point();
        second = new Point();
        third = new Point();
        fourth = new Point();
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

    public void registerTetragonObserver(TetragonObserver tetragonObserver) {
        observers.add(tetragonObserver);
        notifyTetragonObservers();
    }

    public void removeTetragonObserver(TetragonObserver tetragonObserver) {
        observers.remove(tetragonObserver);
        notifyTetragonObservers();
    }

    private void notifyTetragonObservers() {
        observers.forEach(tetragonObserver -> tetragonObserver.update(new TetragonEvent(this)));
    }

    public Point getFirst() {
        return first;
    }

    public void setFirst(Point first) {
        this.first = first;
        notifyTetragonObservers();
    }

    public Point getSecond() {
        return second;
    }

    public void setSecond(Point second) {
        this.second = second;
        notifyTetragonObservers();
    }

    public Point getThird() {
        return third;
    }

    public void setThird(Point third) {
        this.third = third;
        notifyTetragonObservers();
    }

    public Point getFourth() {
        return fourth;
    }

    public void setFourth(Point fourth) {
        this.fourth = fourth;
        notifyTetragonObservers();
    }

    @Override
    public boolean equals(Object object) {
        if ( this == object ) {
            return true;
        }
        if ( (object == null) || (getClass() != object.getClass()) ) {
            return false;
        }

        Tetragon tetragon = (Tetragon) object;

        if ( first != null ? !first.equals(tetragon.first) : tetragon.first != null ) {
            return false;
        }
        if ( second != null ? !second.equals(tetragon.second) : tetragon.second != null ) {
            return false;
        }
        if ( third != null ? !third.equals(tetragon.third) : tetragon.third != null ) {
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
