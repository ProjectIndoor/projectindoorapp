package de.hft_stuttgart.sw.projectindoorapp.models.external;

/**
 * Created by usman on 04-Dec-17.
 */

public class RssiSignal {
    private Long id;
    private double appTimestamp;
    private double rssiSignalStrength;
    private boolean averagedSignalStrength;

    public Long getId() {
        return id;
    }

    public RssiSignal setId(Long id) {
        this.id = id;
        return this;
    }

    public double getAppTimestamp() {
        return appTimestamp;
    }

    public RssiSignal setAppTimestamp(double appTimestamp) {
        this.appTimestamp = appTimestamp;
        return this;
    }

    public double getRssiSignalStrength() {
        return rssiSignalStrength;
    }

    public RssiSignal setRssiSignalStrength(double rssiSignalStrength) {
        this.rssiSignalStrength = rssiSignalStrength;
        return this;
    }

    public boolean isAveragedSignalStrength() {
        return averagedSignalStrength;
    }

    public RssiSignal setAveragedSignalStrength(boolean averagedSignalStrength) {
        this.averagedSignalStrength = averagedSignalStrength;
        return this;
    }
}
