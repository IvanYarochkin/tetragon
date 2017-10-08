package com.yarachkin.tetragon.tetragonentity.dto;

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
    public String toString() {
        return firstPoint.getX() + " " + firstPoint.getY() + " " + secondPoint.getX() + " " + secondPoint.getY() + " " +
                "" + thirdPoint.getX() + " " + thirdPoint.getY() + " " + fourthPoint.getX() + " " + fourthPoint.getY();
    }
}
