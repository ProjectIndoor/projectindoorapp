package de.hft_stuttgart.sw.projectindoorapp.models.external;

import java.util.List;

/**
 * Created by usman on 04-Dec-17.
 */

public class RadioMap {
    private Long id;
    protected RadioMap(){}
    public Long getId() {
        return id;
    }

    private List<RadioMapElement> radioMapElements;

    public List<RadioMapElement> getRadioMapElements() {
        return radioMapElements;
    }

    public RadioMap setRadioMapElements(List<RadioMapElement> radioMapElements) {
        this.radioMapElements = radioMapElements;
        return this;
    }


    public RadioMap setId(Long id) {
        this.id = id;
        return this;
    }


}
