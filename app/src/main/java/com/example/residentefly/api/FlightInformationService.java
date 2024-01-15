package com.example.residentefly.api;

import com.example.residentefly.api.jsonmodels.AmadeusAuthorization;
import com.example.residentefly.api.jsonmodels.AmadeusAuthorizationRequest;
import com.example.residentefly.api.jsonmodels.Escala;
import com.example.residentefly.api.jsonmodels.FlightOfferDTO;
import com.example.residentefly.api.jsonmodels.FlightOfferResponse;
import com.example.residentefly.domain.Money;
import com.example.residentefly.domain.MyBookingInfo;
import com.example.residentefly.domain.flightoffer.Airline;
import com.example.residentefly.domain.flightoffer.Airport;
import com.example.residentefly.domain.flightoffer.Flight;
import com.example.residentefly.domain.flightoffer.FlightOffer;
import com.example.residentefly.domain.flightoffer.FlightOfferRepository;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Response;

public class FlightInformationService implements FlightOfferRepository {
    private AmadeusAuthorization authorization() throws IOException {
        MyApiService apiService = MyApiAdapter.getApiService();
        AmadeusAuthorizationRequest request = new AmadeusAuthorizationRequest();
        Call<AmadeusAuthorization> call = apiService.authorization(request.getGrantType(), request.getClientId(), request.getClientSecret());
        try {
            Response<AmadeusAuthorization> response = call.execute();
            if (response.isSuccessful()) {
                return response.body();
            } else {
                throw new RuntimeException("API call was not successful");
            }
        } catch (
                IOException e) {
            throw new RuntimeException("Error executing API call", e);
        }
    }

    private FlightOfferResponse getFlightOffers2(AmadeusAuthorization token, String departureCode, String arrivalCode, String date, int adults) {
        MyApiService apiService = MyApiAdapter.getApiService();
        Call<FlightOfferResponse> call = apiService.getFlightOffers("Bearer " + token.getAccessToken(), departureCode, arrivalCode, date, adults);
        try {
            Response<FlightOfferResponse> response = call.execute();
            if (response.isSuccessful()) {
                return response.body();
            } else {
                throw new RuntimeException();
            }
        } catch (
                IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<FlightOffer> getFlightOffers(MyBookingInfo myBookingInfo) {
        try {
            AmadeusAuthorization authorizationAmadeus = authorization();
            FlightOfferResponse flightOffers2 = getFlightOffers2(authorizationAmadeus, myBookingInfo.getDepartureCode(), myBookingInfo.getArrivalCode(), myBookingInfo.getDate(), myBookingInfo.getAdultsNumber());
            return flightOffers2.getData().stream().map(this::toDomain).collect(Collectors.toList());

        } catch (IOException ioException) {
            ioException.printStackTrace();
            return new ArrayList<>();
        } catch (Exception e) {
            throw e;
        }

    }

    private FlightOffer toDomain(FlightOfferDTO flightOfferDTO) {
        Duration duration = Duration.parse(flightOfferDTO.getItineraries().get(0).getDuration());
        List<Flight> flights = flightOfferDTO.getItineraries().get(0).getEscalas().stream().map(this::toDomain).collect(Collectors.toList());
        Money price = new Money(new BigDecimal(flightOfferDTO.getPrice().getTotal()), Currency.getInstance(flightOfferDTO.getPrice().getCurrency()));
        return new FlightOffer(duration, flights, price);
    }

    // transform Escala to Flight
    private Flight toDomain(Escala escala) {
        return new Flight(
                new Airport(escala.getDeparture().getIataCode()),
                new Airport(escala.getArrival().getIataCode()),
                Instant.parse(escala.getDeparture().getTime() + "Z"),
                Instant.parse(escala.getArrival().getTime() + "Z"),
                new Airline(escala.getCarrierCode()),
                Duration.parse(escala.getDuration())
        );
    }

}
