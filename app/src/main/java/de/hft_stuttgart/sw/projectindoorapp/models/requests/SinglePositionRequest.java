package de.hft_stuttgart.sw.projectindoorapp.models.requests;

import java.util.List;

/**
 * Created by moritzschillinger on 04.12.17.
 */

public class SinglePositionRequest {

    private Long buildingIdentifier;
    private Long evaluationFile;
    private List<Long> radioMapFiles;
    private String algorithmType;
    private List<String> projectParameters;
    private boolean withPixelPosition;
    private List<String> wifiReadings;
    private Long projectId;

    public Long getBuildingIdentifier() {
        return buildingIdentifier;
    }

    public SinglePositionRequest setBuildingIdentifier(Long buildingIdentifier) {
        this.buildingIdentifier = buildingIdentifier;
        return this;
    }

    public Long getEvaluationFile() {
        return evaluationFile;
    }

    public SinglePositionRequest setEvaluationFile(Long evaluationFile) {
        this.evaluationFile = evaluationFile;
        return this;
    }

    public List<Long> getRadioMapFiles() {
        return radioMapFiles;
    }

    public SinglePositionRequest setRadioMapFiles(List<Long> radioMapFiles) {
        this.radioMapFiles = radioMapFiles;
        return this;
    }

    public String getAlgorithmType() {
        return algorithmType;
    }

    public SinglePositionRequest setAlgorithmType(String algorithmType) {
        this.algorithmType = algorithmType;
        return this;
    }

    public List<String> getProjectParameters() {
        return projectParameters;
    }

    public SinglePositionRequest setProjectParameters(List<String> projectParameters) {
        this.projectParameters = projectParameters;
        return this;
    }

    public boolean isWithPixelPosition() {
        return withPixelPosition;
    }

    public SinglePositionRequest setWithPixelPosition(boolean withPixelPosition) {
        this.withPixelPosition = withPixelPosition;
        return this;
    }

    public List<String> getWifiReadings() {
        return wifiReadings;
    }

    public SinglePositionRequest setWifiReadings(List<String> wifiReadings) {
        this.wifiReadings = wifiReadings;
        return this;
    }

    public Long getProjectId() {
        return projectId;
    }

    public SinglePositionRequest setProjectId(Long projectId) {
        this.projectId = projectId;
        return this;
    }
}
