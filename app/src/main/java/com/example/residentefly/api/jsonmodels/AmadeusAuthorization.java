package com.example.residentefly.api.jsonmodels;

import com.google.gson.annotations.SerializedName;

public class AmadeusAuthorization {
    @SerializedName("access_token")
    private String accessToken;

    public AmadeusAuthorization(String accessToken) {
        this.accessToken = accessToken;
    }

    public AmadeusAuthorization() {
    }

    public String getAccessToken() {
        return accessToken;
    }


    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
