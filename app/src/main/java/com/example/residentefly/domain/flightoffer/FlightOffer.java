package com.example.residentefly.domain.flightoffer;


import com.example.residentefly.domain.Money;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;
import java.util.List;

public class FlightOffer {
    private Duration duration;
    private List<Flight> flights;
    private Money price;

    public FlightOffer(Duration duration, List<Flight> flights, Money price) {
        if (flights.isEmpty()) {
            throw new IllegalArgumentException("Unable to create FlightOffer without flights");
        }
        this.duration = duration;
        this.flights = flights;
        this.price = price;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public List<Flight> getFlights() {
        return flights;
    }

    public void setFlights(List<Flight> flights) {
        this.flights = flights;
    }

    public Money getPrice() {
        return price;
    }

    public void setPrice(Money price) {
        this.price = price;
    }

    public Airport getOrigin() {
        return this.flights.get(0).getDepartureAirport();
    }

    public Airport getDestination() {
        return this.flights.get(this.flights.size() - 1).getArrivalAirport();
    }

    public Instant getDepartureTime() {
        return this.flights.get(0).getDepartureTime();
    }

    public Instant getArrivalTime() {
        return this.flights.get(this.flights.size() - 1).getArrivalTime();
    }

    public boolean isDirect() {
        return this.flights.size() == 1;
    }

    public Money getResidentePrice() {
        return new Money(this.price.getAmount().multiply(new BigDecimal("0.25")), this.price.getCurrency());
    }

    public Flight getFirstFlight() {
        return this.flights.get(0);
    }

    public Flight getLastFlight() {
        return this.flights.get(this.flights.size() - 1);
    }
}
