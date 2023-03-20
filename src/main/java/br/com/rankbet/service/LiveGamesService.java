package br.com.rankbet.service;


import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientProperties;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.com.rankbet.model.Game;

@ApplicationScoped
public class LiveGamesService implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String API_TOKEN = "bd207bc594534134b9c38e54847eb956aeab3bc378b54411bdc5245e1272b4af";
    private Client client;
    private WebTarget target;
    private Jsonb jsonb;

    private List<Game> liveGames;

    @PostConstruct
    public void init() {
    	String URL = "https://api.betting-api.com/1xbet/football/live/all?token="+API_TOKEN;
        ClientConfig clientConfig = new ClientConfig()
                .property(ClientProperties.CONNECT_TIMEOUT, 5000)
                .property(ClientProperties.READ_TIMEOUT, 10000);
        client = ClientBuilder.newClient(clientConfig);
        target = client.target(URL);
        jsonb = JsonbBuilder.create();
        liveGames = new ArrayList<>();
        refreshLiveGames();
        
    }

    public List<Game> getAllLiveGames() {
        return liveGames;
    }

    public void refreshLiveGames() {
    	Response response = target.request(MediaType.APPLICATION_JSON).get();
        liveGames = jsonb.fromJson(response.readEntity(String.class), new ArrayList<Game>(){}.getClass().getGenericSuperclass());        
    }
}