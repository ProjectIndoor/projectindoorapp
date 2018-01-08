package de.hft_stuttgart.sw.projectindoorapp.models.external;

/**
 * Created by Sony on 12/4/2017.
 */

class PosiReference {
    private Long id;
    private int positionInSourceFile;
    private int avgNumber;
    private Position referencePosition;
    private double intervalStart;
    private double intervalEnd;
    private Floor floor;

    public Long getId() {
        return id;
    }

    public PosiReference setId(Long id) {
        this.id = id;
        return this;
    }

    public int getPositionInSourceFile() {
        return positionInSourceFile;
    }

    public PosiReference setPositionInSourceFile(int positionInSourceFile) {
        this.positionInSourceFile = positionInSourceFile;
        return this;
    }

    public int getAvgNumber() {
        return avgNumber;
    }

    public PosiReference setAvgNumber(int avgNumber) {
        this.avgNumber = avgNumber;
        return this;
    }

    public Position getReferencePosition() {
        return referencePosition;
    }

    public PosiReference setReferencePosition(Position referencePosition) {
        this.referencePosition = referencePosition;
        return this;
    }

    public double getIntervalStart() {
        return intervalStart;
    }

    public PosiReference setIntervalStart(double intervalStart) {
        this.intervalStart = intervalStart;
        return this;
    }

    public double getIntervalEnd() {
        return intervalEnd;
    }

    public PosiReference setIntervalEnd(double intervalEnd) {
        this.intervalEnd = intervalEnd;
        return this;
    }

    public Floor getFloor() {
        return floor;
    }

    public PosiReference setFloor(Floor floor) {
        this.floor = floor;
        return this;
    }

    protected PosiReference(){}

}
