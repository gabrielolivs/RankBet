package br.com.rankbet.controller;

import br.com.rankbet.enums.AccountType;
import br.com.rankbet.model.UserModel;
import br.com.rankbet.model.dto.UserDTO;
import br.com.rankbet.service.UserService;
import br.com.rankbet.utils.PasswordUtil;
import jakarta.annotation.ManagedBean;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import org.apache.commons.beanutils.BeanUtils;

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
        UserModel userModel = (UserModel)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        BeanUtils.copyProperties(userModel, userDTO);
        return (userService.updateUser(userModel)) ? "sucess" : "error";
    }
}
