package de.hft_stuttgart.sw.projectindoorapp.models.external;

/**
 * Created by usman on 04-Dec-17.
 */

public class WifiPositionResult {
    private double weight;
    protected WifiPositionResult(){}

    public double getWeight() {
        return weight;
    }

    public WifiPositionResult setWeight(double weight) {
        this.weight = weight;
        return this;
    }
}
