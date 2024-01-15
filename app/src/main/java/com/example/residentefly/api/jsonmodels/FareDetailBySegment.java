package com.example.residentefly.api.jsonmodels;

import com.google.gson.annotations.SerializedName;

public class FareDetailBySegment {
    private String segmentId;
    private String cabin;
    private String fareBasis;
    @SerializedName("class")
    private String clase;
    private IncludedCheckedBags includedCheckedBags;

    public String getSegmentId() {
        return segmentId;
    }

    public void setSegmentId(String segmentId) {
        this.segmentId = segmentId;
    }

    public String getCabin() {
        return cabin;
    }

    public void setCabin(String cabin) {
        this.cabin = cabin;
    }

    public String getFareBasis() {
        return fareBasis;
    }

    public void setFareBasis(String fareBasis) {
        this.fareBasis = fareBasis;
    }

    public String getClase() {
        return clase;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }

    public IncludedCheckedBags getIncludedCheckedBags() {
        return includedCheckedBags;
    }

    public void setIncludedCheckedBags(IncludedCheckedBags includedCheckedBags) {
        this.includedCheckedBags = includedCheckedBags;
    }
}
