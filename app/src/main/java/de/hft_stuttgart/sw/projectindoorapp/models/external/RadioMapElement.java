package de.hft_stuttgart.sw.projectindoorapp.models.external;

import java.util.List;

/**
 * Created by usman on 04-Dec-17.
 */

public class RadioMapElement {
    private Long id;
    private PosiReference posiReference;
    private List<RssiSignal> rssiSignals;
    protected RadioMapElement(){}


    public PosiReference getPosiReference() {
        return posiReference;
    }

    public RadioMapElement setPosiReference(PosiReference posiReference) {
        this.posiReference = posiReference;
        return this;
    }

    public List<RssiSignal> getRssiSignals() {
        return rssiSignals;
    }

    public RadioMapElement setRssiSignals(List<RssiSignal> rssiSignals) {
        this.rssiSignals = rssiSignals;
        return this;
    }



    public Long getId() {
        return id;
    }

    public RadioMapElement setId(Long id) {
        this.id = id;
        return this;
    }
}
