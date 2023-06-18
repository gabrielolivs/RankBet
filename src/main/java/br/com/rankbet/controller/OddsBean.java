package br.com.rankbet.controller;

import br.com.rankbet.model.game.Game;
import br.com.rankbet.service.LiveGamesService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.util.ArrayList;
import java.util.List;

@Named
@RequestScoped
public class OddsBean {

    @Inject
    private LiveGamesService liveGamesService;

    private List<Game> odds;

    public void liveOdds(int id, String team) {
        try {
            odds = liveGamesService.getAllLiveOdds(id, team);
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage("myform", new FacesMessage("Erro ao extrair dados da API"));
        }
    }

    public List<Game> getOdds() {
        return odds;
    }

    public void setOdds(List<Game> odds) {
        this.odds = odds;
    }
}
