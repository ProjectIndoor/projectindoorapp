package de.hft_stuttgart.sw.projectindoorapp.models.external;

import java.util.List;

/**
 * Created by usman on 04-Dec-17.
 */

public class Floor {
    private Long id;
    private int level;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getFloorMapUrl() {
        return floorMapUrl;
    }

    public void setFloorMapUrl(String floorMapUrl) {
        this.floorMapUrl = floorMapUrl;
    }
}
