package br.com.rankbet.controller;

import br.com.rankbet.model.UserModel;
import br.com.rankbet.model.dto.UserDTO;
import br.com.rankbet.service.UserService;
import br.com.rankbet.utils.PasswordUtil;
import jakarta.annotation.ManagedBean;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

import java.lang.reflect.InvocationTargetException;

@Named
@RequestScoped
@ManagedBean
public class UpdatePasswordBean {

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    private UserDTO userDTO;

    private UserService userService;

    private PasswordUtil passwordUtil;

    private String newPasseword;


    @PostConstruct
    public void init() {
        userDTO= new UserDTO();
        userService = new UserService();
        passwordUtil = new PasswordUtil();
    }

    public void submit() throws InvocationTargetException, IllegalAccessException {
        UserModel userModel = (UserModel) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        if(validatePassword(userModel)){
            userDTO.setUserPassword(passwordUtil.generateMD5(newPasseword));
            userModel.setUserPassword(userDTO.getUserPassword());
        }
        try{
            userService.updateUser(userModel);
            FacesContext.getCurrentInstance().
                    addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,"SUCESSO", "Message Content"));

        }catch (Exception exception){
            FacesContext.getCurrentInstance().
                    addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error Message", "Message Content"));
        }
    }

    private boolean validatePassword(UserModel userModel){
        return userModel.getUserPassword().equalsIgnoreCase(passwordUtil.generateMD5(userDTO.getUserPassword())) ? true : false;
    }

    public String getNewPasseword() {
        return newPasseword;
    }

    public void setNewPasseword(String newPasseword) {
        this.newPasseword = newPasseword;
    }
}
