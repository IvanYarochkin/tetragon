package com.yarachkin.tetragon.dto;

public class TetragonDto {
    private PointDto first;
    private PointDto second;
    private PointDto third;
    private PointDto fourth;

    public TetragonDto(PointDto first, PointDto second, PointDto third, PointDto fourth) {
        this.first = first;
        this.second = second;
        this.third = third;
        this.fourth = fourth;
    }

    public PointDto getFirst() {
        return first;
    }

    public void setFirst(PointDto first) {
        this.first = first;
    }

    public PointDto getSecond() {
        return second;
    }

    public void setSecond(PointDto second) {
        this.second = second;
    }

    public PointDto getThird() {
        return third;
    }

    public void setThird(PointDto third) {
        this.third = third;
    }

    public PointDto getFourth() {
        return fourth;
    }

    public void setFourth(PointDto fourth) {
        this.fourth = fourth;
    }

    @Override
    public boolean equals(Object o) {
        if ( this == o ) {
            return true;
        }
        if ( o == null || getClass() != o.getClass() ) {
            return false;
        }

        TetragonDto that = (TetragonDto) o;

        if ( first != null ? !first.equals(that.first) : that.first != null ) {
            return false;
        }
        if ( second != null ? !second.equals(that.second) : that.second != null ) {
            return false;
        }
        if ( third != null ? !third.equals(that.third) : that.third != null ) {
            return false;
        }
        return fourth != null ? fourth.equals(that.fourth) : that.fourth == null;
    }

    @Override
    public int hashCode() {
        int result = 1;
        int prime = 31;

        result = result * prime + (first != null ? first.hashCode() : 0);
        result = result * prime + (second != null ? second.hashCode() : 0);
        result = result * prime + (third != null ? third.hashCode() : 0);
        result = result * prime + (fourth != null ? fourth.hashCode() : 0);

        return result;
    }

    @Override
    public String toString() {
        return first.getX() + " " + first.getY() + " " + second.getX() + " " + second.getY() + " " +
                "" + third.getX() + " " + third.getY() + " " + fourth.getX() + " " + fourth.getY();
    }
}
