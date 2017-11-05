package com.yarachkin.tetragon.tetragonmodel.dto;

public class PointDto {
    private String x;
    private String y;

    public PointDto(String x, String y) {
        this.x = x;
        this.y = y;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if ( this == o ) {
            return true;
        }
        if ( o == null || getClass() != o.getClass() ) {
            return false;
        }

        PointDto pointDto = (PointDto) o;

        if ( x != null ? !x.equals(pointDto.x) : pointDto.x != null ) {
            return false;
        }
        return y != null ? y.equals(pointDto.y) : pointDto.y == null;
    }

    @Override
    public int hashCode() {
        int result = 1;
        int prime = 31;

        result = result * prime + x.hashCode();
        result = result * prime + y.hashCode();

        return result;
    }

    @Override
    public String toString() {
        return "PointDto{x = " + x + ", y = " + y + "}";
    }
}
