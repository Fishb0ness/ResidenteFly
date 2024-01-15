package com.example.residentefly.api.jsonmodels;

import com.example.residentefly.api.ApiData;
import com.google.gson.annotations.SerializedName;

public class AmadeusAuthorizationRequest {

    @SerializedName("client_id")
    private String clientId;
    @SerializedName("client_secret")
    private String clientSecret;
    @SerializedName("grant_type")
    private String grantType;

    public AmadeusAuthorizationRequest(String clientId, String clientSecret, String grantType) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.grantType = grantType;
    }

    public AmadeusAuthorizationRequest() {
        // Set your AMADEUS_INFO HERE
        this.clientId = ApiData.CLIENT_ID;
        this.clientSecret = ApiData.CLIENT_KEY;
        this.grantType = ApiData.CLIENT_TYPE;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getGrantType() {
        return grantType;
    }

    public void setGrantType(String grantType) {
        this.grantType = grantType;
    }
}
