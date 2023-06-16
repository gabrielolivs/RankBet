package br.com.rankbet.controller;

import br.com.rankbet.model.dto.UserDTO;
import br.com.rankbet.service.LoginService;
import jakarta.annotation.ManagedBean;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

@Named
@ViewScoped
@ManagedBean
public class AutenticationBean {

    public UserDTO userDTO;

    private LoginService loginService;

    @PostConstruct
    public void init() {
        userDTO= new UserDTO();
        loginService = new LoginService();
    }

    public void submit(){
        loginService.getLogin(userDTO.getEmail(), userDTO.getUserPassword());
    }
}