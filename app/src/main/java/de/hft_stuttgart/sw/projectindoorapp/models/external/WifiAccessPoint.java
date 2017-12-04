package de.hft_stuttgart.sw.projectindoorapp.models.external;

/**
 * Created by usman on 04-Dec-17.
 */

public class WifiAccessPoint {
    private Long id;

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

    private String macAddress;

}
