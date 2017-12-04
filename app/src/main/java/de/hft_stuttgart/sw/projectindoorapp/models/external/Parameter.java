package de.hft_stuttgart.sw.projectindoorapp.models.external;

/**
 * Created by usman on 04-Dec-17.
 */

public class Parameter {
    private Long id;
    private String parameterName;
    private String paramenterValue;
    protected Parameter(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getParameterName() {
        return parameterName;
    }

    public void setParameterName(String parameterName) {
        this.parameterName = parameterName;
    }

    public String getParamenterValue() {
        return paramenterValue;
    }

    public void setParamenterValue(String paramenterValue) {
        this.paramenterValue = paramenterValue;
    }
}
