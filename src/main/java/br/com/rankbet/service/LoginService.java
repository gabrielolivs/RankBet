package br.com.rankbet.service;


import br.com.rankbet.dao.UserDAO;
import br.com.rankbet.model.UserModel;
import br.com.rankbet.utils.PasswordUtil;

public class LoginService {

    private static UserDAO userDAO = new UserDAO();

    public UserModel verifyAValidLogin(String email, String password) {
        String md5Password = PasswordUtil.generateMD5(password);
        UserModel temp = userDAO.findByEmail("joao@example.com");
        if(temp != null){
            if (temp.getUserPassword().equalsIgnoreCase(md5Password)){
                return temp;
            }
            else{
                return null;
            }
        }
        return null;
    }
}
