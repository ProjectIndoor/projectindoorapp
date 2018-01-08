package de.hft_stuttgart.sw.projectindoorapp.models.external;

import java.util.Map;

/**
 * Created by usman on 04-Dec-17.
 */

public class EvaalFile {
    private Long id;
    private boolean evaluationFile;
    private String sourceFileName;
    private String customFileName;
    private int appVersion;
    private Map<Integer, WifiBlock> wifiBlocks;
    private Building recordedInBuilding;
    private Phone recordedByPhone;
    private RadioMap radioMap;

    public Map<Integer, WifiBlock> getWifiBlocks() {
        return wifiBlocks;
    }

    public EvaalFile setWifiBlocks(Map<Integer, WifiBlock> wifiBlocks) {
        this.wifiBlocks = wifiBlocks;
        return this;
    }

    public Building getRecordedInBuilding() {
        return recordedInBuilding;
    }

    public EvaalFile setRecordedInBuilding(Building recordedInBuilding) {
        this.recordedInBuilding = recordedInBuilding;
        return this;
    }

    public Phone getRecordedByPhone() {
        return recordedByPhone;
    }

    public EvaalFile setRecordedByPhone(Phone recordedByPhone) {
        this.recordedByPhone = recordedByPhone;
        return this;
    }

    public RadioMap getRadioMap() {
        return radioMap;
    }

    public EvaalFile setRadioMap(RadioMap radioMap) {
        this.radioMap = radioMap;
        return this;
    }

    protected EvaalFile(){}

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


}
