package br.com.rankbet.controller;

import br.com.rankbet.model.dto.UserDTO;
import br.com.rankbet.service.UserService;
import br.com.rankbet.utils.PasswordUtil;
import jakarta.annotation.ManagedBean;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

import java.lang.reflect.InvocationTargetException;

@Named
@RequestScoped
@ManagedBean
public class UpdateDataBean {

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    private UserDTO userDTO;

    private UserService userService;

    private PasswordUtil passwordUtil;


    @PostConstruct
    public void init() {
        userDTO= new UserDTO();
        userService = new UserService();
        passwordUtil = new PasswordUtil();
    }

    public String submit() throws InvocationTargetException, IllegalAccessException {
        userDTO.setUserPassword(passwordUtil.generateMD5(userDTO.getUserPassword()));
        return (userService.updateUser(userDTO)) ? "sucess" : "error";
    }

}
