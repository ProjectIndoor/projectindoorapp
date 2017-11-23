package de.hft_stuttgart.sw.projectindoorapp.models;

/**
 * Created by Sony on 11/23/2017.
 */

public class RssiSign {
    private int id;
    private float signalStrength;
    private AccessPoint accessPoint;
    private RadioMapElement radioMapElement;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getSignalStrength() {
        return signalStrength;
    }

    public void setSignalStrength(float signalStrength) {
        this.signalStrength = signalStrength;
    }

    public AccessPoint getAccessPoint() {
        return accessPoint;
    }

    public void setAccessPoint(AccessPoint accessPoint) {
        this.accessPoint = accessPoint;
    }

    public RadioMapElement getRadioMapElement() {
        return radioMapElement;
    }

    public void setRadioMapElement(RadioMapElement radioMapElement) {
        this.radioMapElement = radioMapElement;
    }
}
