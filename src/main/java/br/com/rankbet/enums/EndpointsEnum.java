package br.com.rankbet.enums;

public enum EndpointsEnum {
    XBET("http://api.rankbet.tech/1xbet"),
    SBOBET("http://api.rankbet.tech/sbobet"),
    BETWAY("http://api.rankbet.tech/betway"),
    GGBET("http://api.rankbet.tech/ggbet"),
    FONBET("http://api.rankbet.tech/fonbet"),
    MARATHONBET("http://api.rankbet.tech/marathonbet"),
    PARIMATCH("http://api.rankbet.tech/parimatch");
    public String endpoint;
    EndpointsEnum(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getEndpoint(){
        return endpoint;
    }
}