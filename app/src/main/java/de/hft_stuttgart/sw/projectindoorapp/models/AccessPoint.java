package de.hft_stuttgart.sw.projectindoorapp.models;

/**
 * Created by Sony on 11/23/2017.
 */

public class AccessPoint {
    private int id;
    private String macAddress;
    private Position position;

    @Override
    public String toString() {
        return "AccessPoint{" +
                "id=" + id +
                ", macAddress='" + macAddress + '\'' +
                ", position=" + position +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
