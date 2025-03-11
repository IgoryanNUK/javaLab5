package app.product.builders;

import app.product.Coordinates;

public class CoordinatesBuilder {
    private double x;
    private double y;

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public Coordinates createCoordinates() {
        return new Coordinates(x, y);
    }
}
