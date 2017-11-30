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

    public void setId(int id) {
        this.id = id;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(int floorNumber) {
        this.floorNumber = floorNumber;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

}
