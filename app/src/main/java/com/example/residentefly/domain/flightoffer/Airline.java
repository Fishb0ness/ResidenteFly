package com.example.residentefly.domain.flightoffer;

import java.util.HashMap;
import java.util.Map;

public class Airline {
    private static final Map<String, String> airlineCodeToName;

    static {
        Map<String, String> myMap = new HashMap<>();
        // Populate the map with airline carrier codes and full names for airlines
        myMap.put("IB", "Iberia");
        myMap.put("UX", "Air Europa");
        myMap.put("VY", "Vueling Airlines");
        myMap.put("FR", "Ryanair");
        myMap.put("EZY", "easyJet");
        myMap.put("BIO", "Binter Canarias");
        myMap.put("AP", "Air Pullmantur");
        myMap.put("OAW", "Orbest");
        myMap.put("PEV", "Pegasus Airlines");
        myMap.put("JAF", "Jet AirFly");
        myMap.put("VLG", "Vueling Airlines");
        myMap.put("EWE", "Eurowings Europe");
        myMap.put("NAX", "Norwegian Air International");
        myMap.put("LOT", "LOT Polish Airlines");
        myMap.put("TCX", "Thomas Cook Airlines");
        myMap.put("MIB", "Mistral Air");
        myMap.put("JAI", "Jetairfly");
        myMap.put("AHO", "Air Horizont");
        myMap.put("TUI", "TUI Airways");
        myMap.put("EIN", "Aer Lingus");
        myMap.put("DLH", "Lufthansa");
        myMap.put("WZZ", "Wizz Air");
        myMap.put("SAS", "Scandinavian Airlines (SAS)");
        myMap.put("AZA", "Alitalia");
        myMap.put("NT", "Binter Canarias");

        airlineCodeToName = myMap;
    }

    private String code;
    private String name;

    public Airline(String code) {
        this.code = code;
        this.name = airlineCodeToName.getOrDefault(code, code);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}


