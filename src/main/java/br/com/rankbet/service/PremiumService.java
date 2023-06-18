package br.com.rankbet.service;

import java.util.ArrayList;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientProperties;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.json.bind.Jsonb;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.json.bind.JsonbBuilder;

import br.com.rankbet.model.h2h.H2h;


@ApplicationScoped
public class PremiumService implements java.io.Serializable {
    private Client client;
    private WebTarget target;
    private Jsonb jsonb;

    @PostConstruct
    public void init() {
        ClientConfig clientConfig = new ClientConfig()
                .property(ClientProperties.CONNECT_TIMEOUT, 5000)
                .property(ClientProperties.READ_TIMEOUT, 10000);
        client = ClientBuilder.newClient(clientConfig);
    }

    public H2h getH2h(String time, String team1) {
        target = client.target("http://api.rankbet.tech:8000/premium1?time="+time+"&team1="+team1);
        jsonb = JsonbBuilder.create();
        H2h result = jsonb.fromJson(target.request().get().readEntity(String.class), H2h.class);
        return result;

    }
    
}
