package com.example.residentefly.domain.flightoffer;

import com.example.residentefly.domain.MyBookingInfo;

import java.util.List;

public interface FlightOfferRepository {
    List<FlightOffer> getFlightOffers(MyBookingInfo myBookingInfo);
}
