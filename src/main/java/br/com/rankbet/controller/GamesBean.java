package br.com.rankbet.controller;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.primefaces.PrimeFaces;

import java.util.List;
import java.util.stream.Collectors;

import br.com.rankbet.service.LiveGamesService;
import br.com.rankbet.model.Game;

@Named
@RequestScoped
public class GamesBean {
    
	@Inject
    private LiveGamesService liveGamesService;

    private List<Game> games;
    private String searchTerm;
    private List<Game> filteredGames;

    
    public List<Game> getGames() {
        return games;
    }
    
    
    public String getSearchTerm() {
		return searchTerm;
	}


	public void setSearchTerm(String searchTerm) {
		this.searchTerm = searchTerm;
	}


	public List<Game> getFilteredGames() {
		return filteredGames;
	}


	public void setFilteredGames(List<Game> filteredGames) {
		this.filteredGames = filteredGames;
	}


	public void liveGames() {
    	games = liveGamesService.getAllLiveGames();
    	filteredGames = games;
    }

    public void selectTeam(Game game, String team) {
        System.out.println("Selected team: " + team + " for game: " + game.getTitle());
    }

    public void refreshLiveGames() {
        liveGamesService.refreshLiveGames();
        PrimeFaces.current().ajax().update("liveGamesTable");
    }
    
    public void filterGames() {
        if (searchTerm == null || searchTerm.isEmpty()) {
            filteredGames = games;
        } else {
            filteredGames = games.stream()
                .filter(g -> g.getTitle().toLowerCase().contains(searchTerm.toLowerCase()))
                .collect(Collectors.toList());
        }
    }
    
    
}