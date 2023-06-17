package br.com.rankbet.controller;

import br.com.rankbet.model.game.Game;
import br.com.rankbet.service.LiveGamesService;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Named
@SessionScoped
public class OddsBean {

    @Inject
    private LiveGamesService liveGamesService;

    private List<Game> odds;

    public void liveOdds(String team) {
        try {
            odds = liveGamesService.getAllLiveOdds(team);
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

    public void openNewTab(String url) {

        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();

        try {
            externalContext.redirect(url);
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception if necessary
        }
    }
}