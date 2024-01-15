package com.example.residentefly.api.jsonmodels;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FlightOfferDTO {
    private String type;
    private int id;
    private String source;
    private boolean instatTicketingRequired;
    private boolean nonHomogeneous;
    private boolean oneWay;
    private String lastTicketingDate;
    private int numberOfBookableSeats;
    private List<Itinerari> itineraries;
    @SerializedName("price")
    private Price price;
    private PricingOption pricingOptions;
    private List<String> validatingAirlineCodes;
    private List<TravelPricing> travelPricings;

    public FlightOfferDTO() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public boolean isInstatTicketingRequired() {
        return instatTicketingRequired;
    }

    public void setInstatTicketingRequired(boolean instatTicketingRequired) {
        this.instatTicketingRequired = instatTicketingRequired;
    }

    public boolean isNonHomogeneous() {
        return nonHomogeneous;
    }

    public void setNonHomogeneous(boolean nonHomogeneous) {
        this.nonHomogeneous = nonHomogeneous;
    }

    public boolean isOneWay() {
        return oneWay;
    }

    public void setOneWay(boolean oneWay) {
        this.oneWay = oneWay;
    }

    public String getLastTicketingDate() {
        return lastTicketingDate;
    }

    public void setLastTicketingDate(String lastTicketingDate) {
        this.lastTicketingDate = lastTicketingDate;
    }

    public int getNumberOfBookableSeats() {
        return numberOfBookableSeats;
    }

    public void setNumberOfBookableSeats(int numberOfBookableSeats) {
        this.numberOfBookableSeats = numberOfBookableSeats;
    }

    public List<Itinerari> getItineraries() {
        return itineraries;
    }

    public void setItineraries(List<Itinerari> itineraries) {
        this.itineraries = itineraries;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public PricingOption getPricingOptions() {
        return pricingOptions;
    }

    public void setPricingOptions(PricingOption pricingOptions) {
        this.pricingOptions = pricingOptions;
    }

    public List<String> getValidatingAirlineCodes() {
        return validatingAirlineCodes;
    }

    public void setValidatingAirlineCodes(List<String> validatingAirlineCodes) {
        this.validatingAirlineCodes = validatingAirlineCodes;
    }

    public List<TravelPricing> getTravelPricings() {
        return travelPricings;
    }

    public void setTravelPricings(List<TravelPricing> travelPricings) {
        this.travelPricings = travelPricings;
    }
}
