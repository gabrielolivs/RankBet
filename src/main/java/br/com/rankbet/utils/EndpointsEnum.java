package br.com.rankbet.utils;

public enum EndpointsEnum {
    XBET("https://api.betting-api.com/1xbet/football"),
    BETWAY("https://api.betting-api.com/betway/football"),
    GGBET("https://api.betting-api.com/ggbet/football"),
    FONBET("https://api.betting-api.com/fonbet/football"),
    PARIMATCH("https://api.betting-api.com/parimatch/football"),
    SBOBET("https://api.betting-api.com/sbobet/football");
    public String endpoint;
    EndpointsEnum(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getEndpoint(){
        return endpoint;
    }
}
