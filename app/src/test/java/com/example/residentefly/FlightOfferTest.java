package com.example.residentefly;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import com.example.residentefly.domain.Money;
import com.example.residentefly.domain.flightoffer.Airline;
import com.example.residentefly.domain.flightoffer.Airport;
import com.example.residentefly.domain.flightoffer.Flight;
import com.example.residentefly.domain.flightoffer.FlightOffer;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.Currency;
import java.util.List;

public class FlightOfferTest {

    private FlightOffer flightOffer;
    private Flight flight1;
    private Flight flight2;
    private Money price;

    @Before
    public void setUp() {
        Airport departureAirport1 = new Airport("LAX");
        Airport arrivalAirport1 = new Airport("JFK");
        Airline airline1 = new Airline("IB"); // Initialize with Iberia airline code
        flight1 = new Flight(departureAirport1, arrivalAirport1, Instant.now(), Instant.now().plusSeconds(18000), airline1, Duration.ofHours(5));

        Airport departureAirport2 = new Airport("JFK");
        Airport arrivalAirport2 = new Airport("LHR");
        Airline airline2 = new Airline("UX"); // Initialize with Air Europa airline code
        flight2 = new Flight(departureAirport2, arrivalAirport2, Instant.now().plusSeconds(21600), Instant.now().plusSeconds(39600), airline2, Duration.ofHours(5));

        price = new Money(new BigDecimal(200), Currency.getInstance("USD"));
        flightOffer = new FlightOffer(Duration.ofHours(2), Arrays.asList(flight1, flight2), price);
    }

    @Test
    public void testGetDuration() {
        assertEquals(Duration.ofHours(2), flightOffer.getDuration());
    }

    @Test
    public void testGetFlights() {
        List<Flight> expectedFlights = Arrays.asList(flight1, flight2);
        assertEquals(expectedFlights, flightOffer.getFlights());
    }

    @Test
    public void testGetPrice() {
        assertEquals(price, flightOffer.getPrice());
    }

    @Test
    public void testGetOrigin() {
        assertEquals(flight1.getDepartureAirport(), flightOffer.getOrigin());
    }

    @Test
    public void testGetDestination() {
        assertEquals(flight2.getArrivalAirport(), flightOffer.getDestination());
    }

    @Test
    public void testGetDepartureTime() {
        assertEquals(flight1.getDepartureTime(), flightOffer.getDepartureTime());
    }

    @Test
    public void testGetArrivalTime() {
        assertEquals(flight2.getArrivalTime(), flightOffer.getArrivalTime());
    }

    @Test
    public void testIsDirect() {
        assertFalse(flightOffer.isDirect());
    }

    @Test
    public void testGetResidentePrice() {
        Money expectedPrice = new Money(price.getAmount().multiply(new BigDecimal(0.25)), price.getCurrency());
        assertEquals(0, expectedPrice.getAmount().compareTo(flightOffer.getResidentePrice().getAmount()));
        assertEquals(expectedPrice.getCurrency(), flightOffer.getResidentePrice().getCurrency());
    }

    @Test
    public void testGetFirstFlight() {
        assertEquals(flight1, flightOffer.getFirstFlight());
    }

    @Test
    public void testGetLastFlight() {
        assertEquals(flight2, flightOffer.getLastFlight());
    }
}