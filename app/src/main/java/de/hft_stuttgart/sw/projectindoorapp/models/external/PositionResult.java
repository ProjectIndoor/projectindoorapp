package de.hft_stuttgart.sw.projectindoorapp.models.external;

/**
 * Created by usman on 04-Dec-17.
 */

public class PositionResult {
    private Long id;
    private double x;
    private double y;
    private double z;
    private boolean wgs84;
    protected PositionResult(){}

    public Long getId() {
        return id;
    }

    public PositionResult setId(Long id) {
        this.id = id;
        return this;
    }

    public double getX() {
        return x;
    }

    public PositionResult setX(double x) {
        this.x = x;
        return this;
    }

    public double getY() {
        return y;
    }

    public PositionResult setY(double y) {
        this.y = y;
        return this;
    }

    public double getZ() {
        return z;
    }

    public PositionResult setZ(double z) {
        this.z = z;
        return this;
    }

    public boolean isWgs84() {
        return wgs84;
    }

    public PositionResult setWgs84(boolean wgs84) {
        this.wgs84 = wgs84;
        return this;
    }
}
