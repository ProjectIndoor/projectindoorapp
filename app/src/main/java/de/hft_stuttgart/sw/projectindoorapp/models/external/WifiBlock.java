package de.hft_stuttgart.sw.projectindoorapp.models.external;

/**
 * Created by usman on 04-Dec-17.
 */

public class WifiBlock {
    private Long id;
    protected WifiBlock(){}

    public Long getId() {
        return id;
    }

    public WifiBlock setId(Long id) {
        this.id = id;
        return this;
    }
}

