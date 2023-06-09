package br.com.rankbet.service;

import br.com.rankbet.dao.UserDAO;
import br.com.rankbet.model.UserModel;
import br.com.rankbet.security.PasswordUtil;
import sun.security.provider.MD5;

public class UserService {

    private UserDAO userDAO;

    public void registerUser(UserModel userModel){
        if(userModel !=null){
            try {
                String md5Password = PasswordUtil.generateMD5(userModel.getUserPassword());
                userModel.setUserPassword(md5Password);
                userDAO.cadastrar(userModel);
            }catch (Exception exception){
                exception.printStackTrace();
            }

        }
    }



    public UserModel getUser(UserModel userModel){
        return userDAO.getByEmail(userModel.getEmail());
    }


}
