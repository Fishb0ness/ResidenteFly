package com.example.residentefly.api.jsonmodels;

public class Escala {
    private DepartureArrival departure;
    private DepartureArrival arrival;
    private String carrierCode;
    private String number;
    private Aircraft aircraft;
    private Operating operating;
    private String duration;
    private String id;
    private int numberOfStops;
    private boolean blacklistedInEU;

    public DepartureArrival getDeparture() {
        return departure;
    }

    public void setDeparture(DepartureArrival departure) {
        this.departure = departure;
    }

    public DepartureArrival getArrival() {
        return arrival;
    }

    public void setArrival(DepartureArrival arrival) {
        this.arrival = arrival;
    }

    public String getCarrierCode() {
        return carrierCode;
    }

    public void setCarrierCode(String carrierCode) {
        this.carrierCode = carrierCode;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Aircraft getAircraft() {
        return aircraft;
    }

    public void setAircraft(Aircraft aircraft) {
        this.aircraft = aircraft;
    }

    public Operating getOperating() {
        return operating;
    }

    public void setOperating(Operating operating) {
        this.operating = operating;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getNumberOfStops() {
        return numberOfStops;
    }

    public void setNumberOfStops(int numberOfStops) {
        this.numberOfStops = numberOfStops;
    }

    public boolean isBlacklistedInEU() {
        return blacklistedInEU;
    }

    public void setBlacklistedInEU(boolean blacklistedInEU) {
        this.blacklistedInEU = blacklistedInEU;
    }
}
