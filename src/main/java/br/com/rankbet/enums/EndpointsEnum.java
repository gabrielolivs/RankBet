package br.com.rankbet.enums;

public enum EndpointsEnum {
    XBET("http://api.rankbet.tech:8000/1xbet"),
    SBOBET("http://api.rankbet.tech:8000/sbobet"),
    BETWAY("http://api.rankbet.tech:8000/betway"),
    GGBET("http://api.rankbet.tech:8000/ggbet"),
    FONBET("http://api.rankbet.tech:8000/fonbet"),
    MARATHONBET("http://api.rankbet.tech:8000/marathonbet"),
    PINNACLE("http://api.rankbet.tech:8000/pinnacle"),
    PARIMATCH("http://api.rankbet.tech:8000/parimatch");
    public String endpoint;
    EndpointsEnum(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getEndpoint(){
        return endpoint;
    }
}