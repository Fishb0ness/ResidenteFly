package com.example.residentefly.domain.flightoffer;

import java.util.HashMap;
import java.util.Map;

public class Airport {
    private static final Map<String, String> airportCodeToName;

    static {
        Map<String, String> myMap = new HashMap<>();
        myMap.put("LPA", "Las Palmas de Gran Canaria");
        myMap.put("TFN", "Tenerife Norte");
        myMap.put("TFS", "Tenerife Sur");
        myMap.put("SPC", "La Palma");
        myMap.put("GMZ", "La Gomera");
        myMap.put("VDE", "El Hierro");
        myMap.put("ACE", "Lanzarote");
        myMap.put("FUE", "Fuerteventura");
        myMap.put("MAD", "Madrid Barajas");
        myMap.put("BCN", "Barcelona");
        myMap.put("AGP", "Málaga");
        myMap.put("ALC", "Alicante");
        myMap.put("SVQ", "Sevilla");
        myMap.put("PMI", "Palma de Mallorca");
        myMap.put("IBZ", "Ibiza");
        myMap.put("VLC", "Valencia");
        myMap.put("BIO", "Bilbao");
        myMap.put("SCQ", "Santiago de Compostela");
        myMap.put("LCG", "A Coruña");
        myMap.put("OVD", "Asturias");
        myMap.put("VGO", "Vigo");
        myMap.put("GRX", "Granada");
        myMap.put("SDR", "Santander");
        myMap.put("ZAZ", "Zaragoza");
        myMap.put("LEU", "Lleida");
        myMap.put("XRY", "Jerez de la Frontera");
        myMap.put("PNA", "Pamplona");
        myMap.put("LEN", "León");
        myMap.put("VLL", "Valladolid");
        myMap.put("BJZ", "Badajoz");
        myMap.put("RGS", "Burgos");
        myMap.put("CQM", "Ciudad Real");
        myMap.put("ODB", "Córdoba");
        myMap.put("MLN", "Melilla");
        myMap.put("MJV", "Murcia");
        myMap.put("REU", "Reus");
        myMap.put("GRO", "Girona");
        myMap.put("EAS", "San Sebastián");
        myMap.put("LEI", "Almería");
        myMap.put("LHR", "London");
        myMap.put("CDG", "Paris");
        myMap.put("FRA", "Frankfurt");
        myMap.put("IST", "Istanbul");
        myMap.put("AMS", "Amsterdam");
        myMap.put("FCO", "Rome");
        myMap.put("CPH", "Copenhagen");
        myMap.put("ZRH", "Zurich");
        myMap.put("VIE", "Vienna");
        myMap.put("ATH", "Athens");
        myMap.put("PRG", "Prague");
        myMap.put("WAW", "Warsaw");
        myMap.put("BER", "Berlin");
        myMap.put("MUC", "Munich");
        myMap.put("DUB", "Dublin");
        myMap.put("LIS", "Lisbon");
        myMap.put("MXP", "Milan");
        myMap.put("BRU", "Brussels");

        airportCodeToName = myMap;
    }

    private final String code;
    private final String name;


    public Airport(String code) {
        this.code = code;
        this.name = airportCodeToName.getOrDefault(code, code);
    }

    public String getCode() {
        return this.code;
    }

    public String getName() {
        return this.name;
    }
}

