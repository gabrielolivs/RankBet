package br.com.rankbet.controller;

import br.com.rankbet.model.game.Game;
import br.com.rankbet.service.LiveGamesService;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;

import java.io.IOException;
import java.util.List;


@Named
@RequestScoped
public class OddsBean implements java.io.Serializable {

    @Inject
    private LiveGamesService liveGamesService;

    private List<Game> odds;

    public void liveOdds(String id) {
        try {
            odds = liveGamesService.getAllLiveOdds(id);
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

    public void premium(Game odd) throws IOException {
        FacesContext.getCurrentInstance().getExternalContext()
                .redirect("premium.xhtml?time="+odd.getTime()+"&team1="+odd.getTeam1());
    }

}