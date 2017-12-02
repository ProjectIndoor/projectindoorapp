package de.hft_stuttgart.sw.projectindoorapp.models;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Sony on 11/23/2017.
 */

public class RadioMapElement extends RealmObject {
    @PrimaryKey
    private  int id;

    @Ignore
    private Position referencePosition;

    @Override
    public String toString() {
        return "RadioMapElement{" +
                "id=" + id +
                ", referencePosition=" + referencePosition +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Position getReferencePosition() {
        return referencePosition;
    }

    public void setReferencePosition(Position referencePosition) {
        this.referencePosition = referencePosition;
    }
}
