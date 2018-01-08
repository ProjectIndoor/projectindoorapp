package de.hft_stuttgart.sw.projectindoorapp.models.external;

/**
 * Created by usman on 04-Dec-17.
 */

public class PositionReference {
    private Long id;
    private int positionInSourceFile;
    private int avgNumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getPositionInSourceFile() {
        return positionInSourceFile;
    }

    public void setPositionInSourceFile(int positionInSourceFile) {
        this.positionInSourceFile = positionInSourceFile;
    }

    public int getAvgNumber() {
        return avgNumber;
    }

    public void setAvgNumber(int avgNumber) {
        this.avgNumber = avgNumber;
    }
}
