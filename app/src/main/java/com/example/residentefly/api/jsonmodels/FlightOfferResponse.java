package com.example.residentefly.api.jsonmodels;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FlightOfferResponse {
    @SerializedName("meta")
    private Meta metaData;
    @SerializedName("data")
    private List<FlightOfferDTO> data;
    private Dictionaries dictionaries;

    public FlightOfferResponse() {
    }

    public FlightOfferResponse(Meta metaData, List<FlightOfferDTO> data, Dictionaries dictionaries) {
        this.metaData = metaData;
        this.data = data;
        this.dictionaries = dictionaries;
    }

    public Meta getMetaData() {
        return metaData;
    }

    public void setMetaData(Meta metaData) {
        this.metaData = metaData;
    }

    public List<FlightOfferDTO> getData() {
        return data;
    }

    public void setData(List<FlightOfferDTO> data) {
        this.data = data;
    }

    public Dictionaries getDictionaries() {
        return dictionaries;
    }

    public void setDictionaries(Dictionaries dictionaries) {
        this.dictionaries = dictionaries;
    }
}
