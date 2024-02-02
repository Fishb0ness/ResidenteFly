package com.example.residentefly.domain.flightoffer;

import java.util.HashMap;
import java.util.Map;

public class Airline {
    private static final Map<String, Airline> airlineCodeToAirline;

    static {
        Map<String, Airline> myMap = new HashMap<>();
        // Populate the map with airline carrier codes and full names for airlines
        myMap.put("IB", new Airline("IB", "Iberia", "https://www.iberia.com/"));
        myMap.put("UX", new Airline("UX", "Air Europa", "https://www.aireuropa.com/"));
        myMap.put("VY",  new Airline("VY", "Vueling Airlines", "https://www.vueling.com/"));
        myMap.put("FR",  new Airline("FR", "Ryanair", "https://www.ryanair.com/"));
        myMap.put("EZY", new Airline( "EZY", "easyJet", "https://www.easyjet.com/"));
        myMap.put("BIO", new Airline( "BIO", "Binter Canarias", "https://www.bintercanarias.com/"));
        myMap.put("AP",  new Airline("AP", "Air Pullmantur", "https://www.airpullmantur.com/"));
        myMap.put("OAW", new Airline( "OAW", "Helvetic", "https://www.helvetic.com/"));
        myMap.put("LX",  new Airline("LX", "Swiss International Air Lines", "https://www.swiss.com/"));
        myMap.put("LH",  new Airline("LH", "Lufthansa", "https://www.lufthansa.com/"));
        myMap.put("OS",  new Airline("OS", "Austrian Airlines", "https://www.austrian.com/"));
        myMap.put("TK",  new Airline("TK", "Turkish Airlines", "https://www.turkishairlines.com/"));
        myMap.put("SK",  new Airline("SK", "SAS", "https://www.flysas.com/"));
        myMap.put("DY",  new Airline("DY", "Norwegian", "https://www.norwegian.com/"));
        myMap.put("D8",  new Airline("D8", "Norwegian Air International", "https://www.norwegian.com/"));
        myMap.put("PC",  new Airline("PC", "Pegasus", "https://www.flypgs.com/"));
        myMap.put("PEV", new Airline( "PEV", "Pegasus Airlines", "https://www.flypgs.com/"));
        myMap.put("JAF", new Airline( "JAF", "Jetairfly", "https://www.tuifly.be/"));
        myMap.put("VLG", new Airline( "VLG", "Vueling Airlines", "https://www.vueling.com/"));
        myMap.put("EWE", new Airline( "EWE", "Eurowings", "https://www.eurowings.com/"));
        myMap.put("NAX", new Airline( "NAX", "Norwegian Air Shuttle", "https://www.norwegian.com/"));
        myMap.put("LOT", new Airline( "LOT", "LOT Polish Airlines", "https://www.lot.com/"));
        myMap.put("TCX", new Airline( "TCX", "Thomas Cook Airlines", "https://www.thomascookairlines.com/"));
        myMap.put("MIB", new Airline( "MIB", "Miniliner", "https://www.miniliner.com/"));
        myMap.put("JAI", new Airline( "JAI", "JetSMART", "https://www.jetsmart.com/"));
        myMap.put("AHO", new Airline( "AHO", "Air Hamburg", "https://www.air-hamburg.de/"));
        myMap.put("TUI", new Airline( "TUI", "TUIfly", "https://www.tuifly.com/"));
        myMap.put("EIN", new Airline( "EIN", "Aer Lingus", "https://www.aerlingus.com/"));
        myMap.put("DLH", new Airline( "DLH", "Lufthansa", "https://www.lufthansa.com/"));
        myMap.put("WZZ", new Airline( "WZZ", "Wizz Air", "https://www.wizzair.com/"));
        myMap.put("SAS", new Airline( "SAS", "Scandinavian Airlines (SAS)", "https://www.flysas.com/"));
        myMap.put("AZA", new Airline( "AZA", "Alitalia", "https://www.alitalia.com/"));
        myMap.put("NT",  new Airline("NT", "Binter Canarias", "https://www.bintercanarias.com/"));
        myMap.put("RYR", new Airline( "RYR", "Ryanair", "https://www.ryanair.com/"));
        myMap.put("BA",  new Airline("BA", "British Airways", "https://www.britishairways.com/"));
        myMap.put("AF",  new Airline("AF", "Air France", "https://www.airfrance.com/"));
        myMap.put("KL",  new Airline("KL", "KLM", "https://www.klm.com/"));


        airlineCodeToAirline = myMap;
    }

    private String code;
    private String name;
    private String website;

    public Airline(String code) {
        this.code = code;
        Airline airline = airlineCodeToAirline.get(code);
        if (airline != null) {
            this.name = airline.getName();
            this.website = airline.getWebsite();
        } else {
            this.name = code;
            this.website = "Link no disponible";
        }
    }

    public Airline(String code, String name, String website) {
        this.code = code;
        this.name = name;
        this.website = website;
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

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
}


