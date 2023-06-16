package br.com.rankbet.service;

import br.com.rankbet.dao.LoginDAO;
import br.com.rankbet.model.UserModel;
import br.com.rankbet.utils.PasswordUtil;

public class LoginService {

    private static LoginDAO loginDAO;

    public UserModel verifyAValidLogin(String email, String password) {
        String md5Password = PasswordUtil.generateMD5(password);
        return loginDAO.authenticate(email,md5Password);
    }

}
