package com.example.residentefly.api.jsonmodels;

import java.util.Map;

public class Dictionaries {
    private Map<String, Locatio> locations;
    private Map<String, String> aircraft;
    private Map<String, String> currencies;
    private Map<String, String> carriers;

    public Map<String, Locatio> getLocations() {
        return locations;
    }

    public void setLocations(Map<String, Locatio> locations) {
        this.locations = locations;
    }

    public Map<String, String> getAircraft() {
        return aircraft;
    }

    public void setAircraft(Map<String, String> aircraft) {
        this.aircraft = aircraft;
    }

    public Map<String, String> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(Map<String, String> currencies) {
        this.currencies = currencies;
    }

    public Map<String, String> getCarriers() {
        return carriers;
    }

    public void setCarriers(Map<String, String> carriers) {
        this.carriers = carriers;
    }
}
