package com.yarachkin.tetragon.tetragonmodel.dto;

public class TetragonDto {
    private PointDto firstPoint;
    private PointDto secondPoint;
    private PointDto thirdPoint;
    private PointDto fourthPoint;

    public TetragonDto(PointDto firstPoint, PointDto secondPoint, PointDto thirdPoint, PointDto fourthPoint) {
        this.firstPoint = firstPoint;
        this.secondPoint = secondPoint;
        this.thirdPoint = thirdPoint;
        this.fourthPoint = fourthPoint;
    }

    public PointDto getFirstPoint() {
        return firstPoint;
    }

    public void setFirstPoint(PointDto firstPoint) {
        this.firstPoint = firstPoint;
    }

    public PointDto getSecondPoint() {
        return secondPoint;
    }

    public void setSecondPoint(PointDto secondPoint) {
        this.secondPoint = secondPoint;
    }

    public PointDto getThirdPoint() {
        return thirdPoint;
    }

    public void setThirdPoint(PointDto thirdPoint) {
        this.thirdPoint = thirdPoint;
    }

    public PointDto getFourthPoint() {
        return fourthPoint;
    }

    public void setFourthPoint(PointDto fourthPoint) {
        this.fourthPoint = fourthPoint;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TetragonDto)) {
            return false;
        }

        TetragonDto that = (TetragonDto) o;

        if (firstPoint != null ? !firstPoint.equals(that.firstPoint) : that.firstPoint != null) {
            return false;
        }
        if (secondPoint != null ? !secondPoint.equals(that.secondPoint) : that.secondPoint != null) {
            return false;
        }
        if (thirdPoint != null ? !thirdPoint.equals(that.thirdPoint) : that.thirdPoint != null) {
            return false;
        }
        return fourthPoint != null ? fourthPoint.equals(that.fourthPoint) : that.fourthPoint == null;
    }

    @Override
    public int hashCode() {
        int result = 1;
        int prime = 31;

        result = result * prime + (firstPoint != null ? firstPoint.hashCode() : 0);
        result = result * prime + (secondPoint != null ? secondPoint.hashCode() : 0);
        result = result * prime + (thirdPoint != null ? thirdPoint.hashCode() : 0);
        result = result * prime + (fourthPoint != null ? fourthPoint.hashCode() : 0);

        return result;
    }

    @Override
    public String toString() {
        return firstPoint.getX() + " " + firstPoint.getY() + " " + secondPoint.getX() + " " + secondPoint.getY() + " " +
                "" + thirdPoint.getX() + " " + thirdPoint.getY() + " " + fourthPoint.getX() + " " + fourthPoint.getY();
    }
}
