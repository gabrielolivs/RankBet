package br.com.rankbet.utils;

public enum EndpointsEnum {
    XBET("https://api.betting-api.com/1xbet/football"),
    SBOBET("https://api.betting-api.com/sbobet/football");
    public String endpoint;
    EndpointsEnum(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getEndpoint(){
        return endpoint;
    }
}
