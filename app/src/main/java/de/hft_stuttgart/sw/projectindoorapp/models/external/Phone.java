package de.hft_stuttgart.sw.projectindoorapp.models.external;

import java.util.List;

/**
 * Created by usman on 04-Dec-17.
 */

public class Phone {
    private Long id;
    private String manufacturer;
    private String model;
    private int apiVersion;
    private List<EvaalFile> recordedFiles;

    public List<EvaalFile> getRecordedFiles() {
        return recordedFiles;
    }

    public Phone setRecordedFiles(List<EvaalFile> recordedFiles) {
        this.recordedFiles = recordedFiles;
        return this;
    }

    protected Phone(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getApiVersion() {
        return apiVersion;
    }

    public void setApiVersion(int apiVersion) {
        this.apiVersion = apiVersion;
    }



}
