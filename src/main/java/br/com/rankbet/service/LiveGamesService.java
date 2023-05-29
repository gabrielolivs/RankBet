package br.com.rankbet.service;


import br.com.rankbet.model.Win1;
import br.com.rankbet.model.Win2;
import br.com.rankbet.model.api.BettingApi;
import br.com.rankbet.model.game.GameAll;
import br.com.rankbet.model.game.GameSbobet;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientProperties;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;

import java.io.Serializable;
import java.util.*;

import br.com.rankbet.model.game.Game;
import br.com.rankbet.utils.EndpointsEnum;

@ApplicationScoped
public class LiveGamesService implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private Client client;
    private WebTarget target;
    private Jsonb jsonb;

    private List<Game> liveGames;


    @PostConstruct
    public void init() {
        ClientConfig clientConfig = new ClientConfig()
                .property(ClientProperties.CONNECT_TIMEOUT, 5000)
                .property(ClientProperties.READ_TIMEOUT, 10000);
        client = ClientBuilder.newClient(clientConfig);
        liveGames = new ArrayList<>();
        refreshLiveGames();
        
    }

    public List<Game> getAllLiveGames() {
        return liveGames;
    }

    public void refreshLiveGames() {
        target = client.target(EndpointsEnum.XBET.getEndpoint()+BettingApi.LIVE_ENDPOINT+BettingApi.API_TOKEN);
        jsonb = JsonbBuilder.create();
    	Response response = target.request(MediaType.APPLICATION_JSON).get();
        liveGames = jsonb.fromJson(response.readEntity(String.class), new ArrayList<Game>(){}.getClass().getGenericSuperclass());
    }

    public List<Game> getAllLiveOdds(int id, String team) {
        List<Game> liveOddsfinal = new ArrayList<Game>();
        for(EndpointsEnum endpoint : EndpointsEnum.values()){
            List<Game> liveOdds = new ArrayList<Game>();
            target = client.target(endpoint.getEndpoint()+BettingApi.LIVE_ENDPOINT+BettingApi.API_TOKEN);
            jsonb = JsonbBuilder.create();
            Response response = target.request(MediaType.APPLICATION_JSON).get();
            if (getType(String.valueOf(endpoint)) == GameSbobet.class) {
                liveOdds = jsonb.fromJson(response.readEntity(String.class), new ArrayList<GameSbobet>() {
                }.getClass().getGenericSuperclass());
            }
            else {
                liveOdds = jsonb.fromJson(response.readEntity(String.class), new ArrayList<GameAll>() {
                }.getClass().getGenericSuperclass());
            }
            liveOdds.stream()
                    .filter(odd -> odd.getTeam1().equals(team) || odd.getTeam2().equals(team))
                    .forEach(liveOddsfinal::add);
        }
        for(Game game: liveOddsfinal){
            if(game.getTeam1().equals(team))
                break;
            else {
                if(game instanceof GameAll){
                    GameAll game1 = (GameAll) game;
                    double win1 = game1.getMarkets().getWin1();
                    game1.getMarkets().setWin1(new Win1(game1.getMarkets().getWin2()));
                    game1.getMarkets().setWin2(new Win2(win1));
                }
                else if(game instanceof GameSbobet){
                    GameSbobet game1 = (GameSbobet) game;
                    double win1 = game1.getMarkets().getWin1();
                    game1.getMarkets().setWin1(game1.getMarkets().getWin2());
                    game1.getMarkets().setWin2(win1);
                }

            }
        }
        return liveOddsfinal;
    }

    public Class<? extends Game> getType(String value){
        if(value.equals("SBOBET")) {
            return GameSbobet.class;
        }
        else return GameAll.class;
    }

}
