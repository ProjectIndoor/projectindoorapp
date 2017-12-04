package de.hft_stuttgart.sw.projectindoorapp.models.external;

/**
 * Created by usman on 04-Dec-17.
 */

public class WifiAccessPoint {
    private Long id;
    private String macAddress;
    private Position position;

    public Position getPosition() {
        return position;
    }

    public WifiAccessPoint setPosition(Position position) {
        this.position = position;
        return this;
    }

    protected WifiAccessPoint(){}


    public Long getId() {
        return id;
    }

    public WifiAccessPoint setId(Long id) {
        this.id = id;
        return this;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public WifiAccessPoint setMacAddress(String macAddress) {
        this.macAddress = macAddress;
        return this;
    }


}
