package de.hft_stuttgart.sw.projectindoorapp.models.external;

import java.util.List;

/**
 * Created by usman on 04-Dec-17.
 */

public class Building {
    private Long id;
    private String buildingName;
    private int imagePixelWidth;
    private int imagePixelHeight;
    public Long getId() {
        return id;
    }
    private Position northWest;
    private Position northEast;
    private Position southEast;
    private Position southWest;
    private List<EvaalFile> evaalFiles;
    private List<Floor> buildingFloors;

    public Building() {
    }

    public Position getNorthEast() {
        return northEast;
    }

    public Building setNorthEast(Position northEast) {
        this.northEast = northEast;
        return this;
    }

    public Position getSouthEast() {
        return southEast;
    }

    public Building setSouthEast(Position southEast) {
        this.southEast = southEast;
        return this;
    }

    public Position getSouthWest() {
        return southWest;
    }

    public Building setSouthWest(Position southWest) {
        this.southWest = southWest;
        return this;
    }

    public List<EvaalFile> getEvaalFiles() {
        return evaalFiles;
    }

    public Building setEvaalFiles(List<EvaalFile> evaalFiles) {
        this.evaalFiles = evaalFiles;
        return this;
    }

    public List<Floor> getBuildingFloors() {
        return buildingFloors;
    }

    public Building setBuildingFloors(List<Floor> buildingFloors) {
        this.buildingFloors = buildingFloors;
        return this;
    }

    public Position getNorthWest() {
        return northWest;
    }

    public void setNorthWest(Position northWest) {
        this.northWest = northWest;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public int getImagePixelWidth() {
        return imagePixelWidth;
    }

    public void setImagePixelWidth(int imagePixelWidth) {
        this.imagePixelWidth = imagePixelWidth;
    }

    public int getImagePixelHeight() {
        return imagePixelHeight;
    }

    public void setImagePixelHeight(int imagePixelHeight) {
        this.imagePixelHeight = imagePixelHeight;
    }


}

