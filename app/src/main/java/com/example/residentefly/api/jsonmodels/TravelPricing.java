package com.example.residentefly.api.jsonmodels;

import java.util.List;

public class TravelPricing {
    private String travelerId;
    private String fareOption;
    private String travelerType;
    private Price price;
    private List<FareDetailBySegment> fareDetailsBySegment;

    public String getTravelerId() {
        return travelerId;
    }

    public void setTravelerId(String travelerId) {
        this.travelerId = travelerId;
    }

    public String getFareOption() {
        return fareOption;
    }

    public void setFareOption(String fareOption) {
        this.fareOption = fareOption;
    }

    public String getTravelerType() {
        return travelerType;
    }

    public void setTravelerType(String travelerType) {
        this.travelerType = travelerType;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public List<FareDetailBySegment> getFareDetailsBySegment() {
        return fareDetailsBySegment;
    }

    public void setFareDetailsBySegment(List<FareDetailBySegment> fareDetailsBySegment) {
        this.fareDetailsBySegment = fareDetailsBySegment;
    }
}
