package com.example.residentefly.api.jsonmodels;

import com.google.gson.annotations.SerializedName;

public class Meta {
    @SerializedName("count")
    private int flyCount;
    private Links links;

    public int getFlyCount() {
        return flyCount;
    }

    public void setFlyCount(int flyCount) {
        this.flyCount = flyCount;
    }

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }
}
