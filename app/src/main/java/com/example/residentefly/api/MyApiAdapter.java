package com.example.residentefly.api;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MyApiAdapter {

    private static final String BASE_URL = "https://test.api.amadeus.com/";
    private static MyApiService API_SERVICE;

    public static MyApiService getApiService() {

        // create OkHttp client
        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttpClientBuilder.addInterceptor(logging);

        // Singleton Pattern
        if (API_SERVICE == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClientBuilder.build())
                    .build();

            API_SERVICE = retrofit.create(MyApiService.class);
        }

        return API_SERVICE;
    }
}