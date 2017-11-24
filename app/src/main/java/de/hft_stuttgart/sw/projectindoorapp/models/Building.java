package de.hft_stuttgart.sw.projectindoorapp.models;

/**
 * Created by Sony on 11/23/2017.
 */

public class Building {
    private int id;
    private String name;
    private Position northEast;
    private Position southWest;

    @Override
    public String toString() {
        return "Building{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", northEast=" + northEast +
                ", southWest=" + southWest +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Position getNorthEast() {
        return northEast;
    }

    public void setNorthEast(Position northEast) {
        this.northEast = northEast;
    }

    public Position getSouthWest() {
        return southWest;
    }

    public void setSouthWest(Position southWest) {
        this.southWest = southWest;
    }
}
