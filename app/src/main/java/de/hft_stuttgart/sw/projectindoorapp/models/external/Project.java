package de.hft_stuttgart.sw.projectindoorapp.models.external;

import java.util.List;

/**
 * Created by usman on 04-Dec-17.
 */

public class Project {
    private Long projectId;
    private String projectName;
    private List<Parameter> projectParameters;
    private List<EvaalFile> evaalFiles;
    private CalculationAlgorithm calculationAlgorithm;
    private String buildingName;

    public List<Parameter> getProjectParameters() {
        return projectParameters;
    }

    public Project setProjectParameters(List<Parameter> projectParameters) {
        this.projectParameters = projectParameters;
        return this;
    }

    public List<EvaalFile> getEvaalFiles() {
        return evaalFiles;
    }

    public Project setEvaalFiles(List<EvaalFile> evaalFiles) {
        this.evaalFiles = evaalFiles;
        return this;
    }

    protected Project() {
    }

    public Long getProjectId() {
        return projectId;
    }

    public Project setProjectId(Long projectId) {
        this.projectId = projectId;
        return this;
    }

    public String getProjectName() {
        return projectName;
    }

    public Project setProjectName(String projectName) {
        this.projectName = projectName;
        return this;
    }

    public CalculationAlgorithm getCalculationAlgorithm() {
        return calculationAlgorithm;
    }

    public Project setCalculationAlgorithm(CalculationAlgorithm calculationAlgorithm) {
        this.calculationAlgorithm = calculationAlgorithm;
        return this;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public Project setBuildingName(String buildingName) {
        this.buildingName = buildingName;
        return this;
    }
}
