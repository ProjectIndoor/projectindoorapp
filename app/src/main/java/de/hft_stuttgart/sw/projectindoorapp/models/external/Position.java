package de.hft_stuttgart.sw.projectindoorapp.models.external;

/**
 * Created by moritzschillinger on 04.12.17.
 */

public class Position {

    private Long id;
    private double x;
    private double y;
    private double z;
    private boolean wgs84;
    public Position(){ }


    public Long getId() {
        return id;
    }

    public Position setId(Long id) {
        this.id = id;
        return this;
    }

    public double getX() {
        return x;
    }

    public Position setX(double x) {
        this.x = x;
        return this;
    }

    public double getY() {
        return y;
    }

    public Position setY(double y) {
        this.y = y;
        return this;
    }

    public double getZ() {
        return z;
    }

    public Position setZ(double z) {
        this.z = z;
        return this;
    }

    public boolean isWgs84() {
        return wgs84;
    }

    public Position setWgs84(boolean wgs84) {
        this.wgs84 = wgs84;
        return this;
    }
}
