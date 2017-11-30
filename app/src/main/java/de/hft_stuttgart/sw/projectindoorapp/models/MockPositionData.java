package de.hft_stuttgart.sw.projectindoorapp.models;

/**
 * Created by deepika on 28.11.17.
 */

public class MockPositionData {

    // Corners of building for which position is to be mocked. Default values initialized
    // for the university building
    Position nortWestCorner = new Position(48.780551, 9.171766);
    Position southEastCorner = new Position(48.780159, 9.173488);
    Position newLocation;
    double latInc;
    double lngInc;


    // default consturctor. North west and south east Corners are fixed
    public MockPositionData() {
        initStartLocation();
    }

    // Constructor with north west corner and south east corner specified by user
    public MockPositionData(Position nwCorner, Position seCorner) {
        nortWestCorner = nwCorner;
        southEastCorner = seCorner;
        initStartLocation();
    }


    // initialize the random start position within the bounds of building and set the
    // increment level for each call to getPosition()
    private void initStartLocation() {

        double latStart = Math.random();
        double lngStart = Math.random();

        // Math.random() returns number between 0.0 and 1.0. Make sure start location is
        // within the bounding area
        while (latStart > (nortWestCorner.getLatitude() - southEastCorner.getLatitude())) {
            latStart = latStart / 10;
        }

        latInc = latStart / 10;  // increment latitude in small steps
        latStart = nortWestCorner.getLatitude() - latStart;

        while (lngStart > southEastCorner.getLongitude() - nortWestCorner.getLongitude()) {
            lngStart = lngStart / 10;
        }

        lngInc = lngStart / 10;   // increment longitude in small steps
        lngStart = nortWestCorner.getLongitude() + lngStart;

        newLocation = new Position(latStart, lngStart);
    }


    // returns the user position within building bounds with small increments in steps
    public Position getPosition() {

        Position currentPosition = newLocation;

        double newLat = currentPosition.getLatitude() + latInc;
        double newLng = currentPosition.getLongitude() + lngInc;

        if ((newLat > nortWestCorner.getLatitude()) ||
                (newLat < southEastCorner.getLatitude())) {
            newLat = currentPosition.getLatitude();   // don't go outside border
            latInc = -latInc;       // start moving in other direction
        }
        if ((newLng > southEastCorner.getLongitude()) ||
                (newLng < nortWestCorner.getLongitude())) {
            newLng = currentPosition.getLongitude();
            lngInc = -lngInc;
        }

        newLocation.setLatitude(newLat);
        newLocation.setLongitude(newLng);

        return currentPosition;
    }

}