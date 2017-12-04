package de.hft_stuttgart.sw.projectindoorapp.models.external;

/**
 * Created by usman on 04-Dec-17.
 */

public class EvaalFile {
    private Long id;
    private boolean evaluationFile;
    private String sourceFileName;
    private String customFileName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isEvaluationFile() {
        return evaluationFile;
    }

    public void setEvaluationFile(boolean evaluationFile) {
        this.evaluationFile = evaluationFile;
    }

    public String getSourceFileName() {
        return sourceFileName;
    }

    public void setSourceFileName(String sourceFileName) {
        this.sourceFileName = sourceFileName;
    }

    public String getCustomFileName() {
        return customFileName;
    }

    public void setCustomFileName(String customFileName) {
        this.customFileName = customFileName;
    }

    public int getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(int appVersion) {
        this.appVersion = appVersion;
    }

    private int appVersion;
}
