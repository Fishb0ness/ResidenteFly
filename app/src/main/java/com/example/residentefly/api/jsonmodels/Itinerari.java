package com.example.residentefly.api.jsonmodels;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Itinerari {
    private String duration;
    @SerializedName("segments")
    private List<Escala> escalas;

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public List<Escala> getEscalas() {
        return escalas;
    }

    public void setEscalas(List<Escala> escalas) {
        this.escalas = escalas;
    }
}
