package de.hft_stuttgart.sw.projectindoorapp.models.external;

import java.util.List;

/**
 * Created by usman on 04-Dec-17.
 */

public class WifiBlock {
    private Long id;
    protected WifiBlock(){}
    private List<String> wifiLines;

    public List<String> getWifiLines() {
        return wifiLines;
    }

    public WifiBlock setWifiLines(List<String> wifiLines) {
        this.wifiLines = wifiLines;
        return this;
    }

    public Long getId() {
        return id;
    }

    public WifiBlock setId(Long id) {
        this.id = id;
        return this;
    }
}

