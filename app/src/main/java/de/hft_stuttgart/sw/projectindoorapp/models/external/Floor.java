package de.hft_stuttgart.sw.projectindoorapp.models.external;

import java.util.List;

/**
 * Created by usman on 04-Dec-17.
 */

public class Floor {
    private Long floorId;
    private int floorLevel;
    private String floorMapUrl;
    private List<PosiReference> posiReferences;

    public List<PosiReference> getPosiReferences() {
        return posiReferences;
    }

    public Floor setPosiReferences(List<PosiReference> posiReferences) {
        this.posiReferences = posiReferences;
        return this;
    }

    protected Floor(){}

    public Long getFloorId() {
        return floorId;
    }

    public Floor setFloorId(Long floorId) {
        this.floorId = floorId;
        return this;
    }

    public int getFloorLevel() {
        return floorLevel;
    }

    public Floor setFloorLevel(int floorLevel) {
        this.floorLevel = floorLevel;
        return this;
    }

    public String getFloorMapUrl() {
        return floorMapUrl;
    }

    public void setFloorMapUrl(String floorMapUrl) {
        this.floorMapUrl = floorMapUrl;
    }
}
