package com.example.residentefly;

import com.example.residentefly.domain.Money;
import com.example.residentefly.domain.flightoffer.Airline;
import com.example.residentefly.domain.flightoffer.Airport;
import com.example.residentefly.domain.flightoffer.Flight;
import com.example.residentefly.domain.flightoffer.FlightOffer;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.Currency;
import java.util.List;

import static org.junit.Assert.*;

import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

@RunWith(RobolectricTestRunner.class)
public class MainActivity2FlightsViewTest {

    private MainActivity2FlightsView mainActivity2FlightsView;
    private List<FlightOffer> flightOffers;

    @Before
    public void setUp() {
        mainActivity2FlightsView = new MainActivity2FlightsView();

        Airport departureAirport1 = new Airport("LAX");
        Airport arrivalAirport1 = new Airport("JFK");
        Airline airline1 = new Airline("IB"); // Initialize with Iberia airline code
        Flight flight1 = new Flight(departureAirport1, arrivalAirport1, Instant.now(), Instant.now().plusSeconds(18000), airline1, Duration.ofHours(5));

        Airport departureAirport2 = new Airport("JFK");
        Airport arrivalAirport2 = new Airport("LHR");
        Airline airline2 = new Airline("UX"); // Initialize with Air Europa airline code
        Flight flight2 = new Flight(departureAirport2, arrivalAirport2, Instant.now().plusSeconds(21600), Instant.now().plusSeconds(39600), airline2, Duration.ofHours(5));

        List<Flight> flights1 = Arrays.asList(flight1);
        List<Flight> flights2 = Arrays.asList(flight1, flight2);
        Money price1 = new Money(new BigDecimal(200), Currency.getInstance("USD"));
        Money price2 = new Money(new BigDecimal(300), Currency.getInstance("USD"));
        FlightOffer flightOffer1 = new FlightOffer(Duration.ofHours(5), flights1, price1);
        FlightOffer flightOffer2 = new FlightOffer(Duration.ofHours(6), flights2, price2);
        flightOffers = Arrays.asList(flightOffer1, flightOffer2);
    }

    @Test
    public void testFilterSortFlightOffer_Duration() {
        List<FlightOffer> result = mainActivity2FlightsView.filterSortFlightOffer(flightOffers, "Duracion", false);
        assertEquals(2, result.size());
        assertEquals(Duration.ofHours(5), result.get(0).getDuration());
        assertEquals(Duration.ofHours(6), result.get(1).getDuration());
    }

    @Test
    public void testFilterSortFlightOffer_Price() {
        List<FlightOffer> result = mainActivity2FlightsView.filterSortFlightOffer(flightOffers, "Precio", false);
        assertEquals(2, result.size());
        assertEquals(new BigDecimal(200), result.get(0).getPrice().getAmount());
        assertEquals(new BigDecimal(300), result.get(1).getPrice().getAmount());
    }

    @Test
    public void testFilterSortFlightOffer_Direct() {
        List<FlightOffer> result = mainActivity2FlightsView.filterSortFlightOffer(flightOffers, "Duracion", true);
        assertEquals(1, result.size());
        assertEquals(Duration.ofHours(5), result.get(0).getDuration());
    }
}