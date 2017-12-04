package de.hft_stuttgart.sw.projectindoorapp.models;

/**
 * Created by Sony on 11/23/2017.
 */

public class Position {
    private int id;
    private double latitude;
    private double longitude;
    private int floorNumber;
    private float height;

    @Override
    public String toString() {
        return "Position{" +
                "id=" + id +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", floorNumber=" + floorNumber +
                ", height=" + height +
                '}';
    }

    public Position() {

    }

    public Position(double lat, double lng) {
        latitude = lat;
        longitude = lng;
    }

    public int getId() {
        return id;
    }

    public Position setId(int id) {
        this.id = id;
        return this;
    }

    public double getLatitude() {
        return latitude;
    }

    public Position setLatitude(double latitude) {
        this.latitude = latitude;
        return this;
    }

    public double getLongitude() {
        return longitude;
    }

    public Position setLongitude(double longitude) {
        this.longitude = longitude;
        return this;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public Position setFloorNumber(int floorNumber) {
        this.floorNumber = floorNumber;
        return this;
    }

    public float getHeight() {
        return height;
    }

    public Position setHeight(float height) {
        this.height = height;
        return this;
    }
}
