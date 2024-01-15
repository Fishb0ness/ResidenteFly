package com.example.residentefly.domain;

import java.io.Serializable;

public class MyBookingInfo implements Serializable {
    private String departureCode, arrivalCode;
    private String date;
    private int adultsNumber;

    public MyBookingInfo() {
    }

    public String getDepartureCode() {
        return departureCode;
    }

    public void setDepartureCode(String departureCode) {
        this.departureCode = departureCode;
    }

    public String getArrivalCode() {
        return arrivalCode;
    }

    public void setArrivalCode(String arrivalCode) {
        this.arrivalCode = arrivalCode;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getAdultsNumber() {
        return adultsNumber;
    }

    public void setAdultsNumber(int adultsNumber) {
        this.adultsNumber = adultsNumber;
    }
}
