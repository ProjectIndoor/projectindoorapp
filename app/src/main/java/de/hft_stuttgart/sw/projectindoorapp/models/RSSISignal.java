package de.hft_stuttgart.sw.projectindoorapp.models;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Sony on 11/23/2017.
 */

public class RSSISignal extends RealmObject {

    @PrimaryKey
    private int id;
    private int signalStrength;
    private AccessPoint accessPoint;
    private RadioMapElement radioMapElement;

    @Override
    public String toString() {
        return "RSSISignal{" +
                "id=" + id +
                ", signalStrength=" + signalStrength +
                ", accessPoint=" + accessPoint +
                ", radioMapElement=" + radioMapElement +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSignalStrength() {
        return signalStrength;
    }

    public void setSignalStrength(int signalStrength) {
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
