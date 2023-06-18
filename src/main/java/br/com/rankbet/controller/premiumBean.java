package br.com.rankbet.controller;

import br.com.rankbet.model.h2h.H2h;
import br.com.rankbet.service.PremiumService;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;


@Named
@RequestScoped
public class premiumBean implements java.io.Serializable {
    
    @Inject
    private PremiumService PremiumService;

    private H2h h2h;

    public void geth2h(String time, String team1) {
        try {
            h2h = PremiumService.getH2h(time, team1);
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage("myform", new FacesMessage("Erro ao extrair dados da API"));
        }
    }

    public H2h getH2h() {
        return h2h;
    }

    public void setH2h(H2h h2h) {
        this.h2h = h2h;
    }
}
