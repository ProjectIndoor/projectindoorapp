package de.hft_stuttgart.sw.projectindoorapp.models.external;

/**
 * Created by usman on 04-Dec-17.
 */

public class Project {
    private Long id;
    private String projectName;

    public Long getId() {
        return id;
    }

    public Project setId(Long id) {
        this.id = id;
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

    private CalculationAlgorithm calculationAlgorithm;
}
