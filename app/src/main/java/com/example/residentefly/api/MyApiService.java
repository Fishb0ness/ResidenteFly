package com.example.residentefly.api;


import com.example.residentefly.api.jsonmodels.AmadeusAuthorization;
import com.example.residentefly.api.jsonmodels.FlightOfferResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface MyApiService {

    @Headers("Content-Type: application/x-www-form-urlencoded")
    @FormUrlEncoded
    @POST("v1/security/oauth2/token")
    Call<AmadeusAuthorization> authorization(
            @Field("grant_type") String grantType,
            @Field("client_id") String clientId,
            @Field("client_secret") String clientSecret
    );

    //Return list of Flight Offers based on searching criteria.
    @GET("v2/shopping/flight-offers")
    Call<FlightOfferResponse> getFlightOffersWithFullInfo(
            //REQUIRED
            @Query("originLocationCode") String originIataCode,
            //REQUIRED
            @Query("destinationLocationCode") String arrivalIataCode,
            //REQUIRED
            //Dates are specified in the ISO 8601 YYYY-MM-DD format, e.g. 2017-12-25
            @Query("departureDate") String departureDate,
            @Query("returnDate") String returnDate,
            //REQUIRED
            @Query("adults") int adultPassengersQuantity,
            @Query("children") int childrenPassengersQuantity,
            @Query("infants") int infantsPassengersQuantity,
            @Query("travelClass") String travelClass,
            //maximum number of flight offers to return.
            // If specified, the value should be greater than or equal to 1.
            //Default value: 250
            @Query("max") int maxOffers
    );

    @GET("v2/shopping/flight-offers")
    Call<FlightOfferResponse> getFlightOffers(
            @Header("Authorization") String token,
            //REQUIRED
            @Query("originLocationCode") String originIataCode,
            //REQUIRED
            @Query("destinationLocationCode") String arrivalIataCode,
            //REQUIRED
            //Dates are specified in the ISO 8601 YYYY-MM-DD format, e.g. 2017-12-25
            @Query("departureDate") String depertureDate,
            //REQUIRED
            @Query("adults") int adultPassengersQuantity
    );

}
