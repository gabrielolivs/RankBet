package br.com.rankbet.service;

import br.com.rankbet.dao.UserDAO;
import br.com.rankbet.model.UserModel;
import br.com.rankbet.model.dto.UserDTO;
import br.com.rankbet.utils.PasswordUtil;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

public class UserService {

    private UserDAO userDAO;

    public void registerUser(UserDTO userDTO) throws InvocationTargetException, IllegalAccessException {
        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(userModel, userDTO);
        if(!userModel.getEmail().isEmpty()){
            try {
                String md5Password = PasswordUtil.generateMD5(userModel.getUserPassword());
                userModel.setUserPassword(md5Password);
                userModel.setUserEnabled(1L);
                userDAO.saveUser(userModel);
            }catch (Exception exception){
                exception.printStackTrace();
            }
        }
    }

    public void updateUser(UserDTO userDTO) throws InvocationTargetException, IllegalAccessException {
        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(userModel, userDTO);
            try {
                if(!userDAO.existsEmail(userModel.getEmail())){
                    userDAO.saveUser(userModel);
                }else{
                    //erro email ja existe
                }
            }catch (Exception exception){
                exception.printStackTrace();
            }
    }

    public void updatePasswordUser(String email, String newPassword){
        if(email != null && newPassword != null){
            try{
                UserModel user = userDAO.getByEmail(email);
                if(!Objects.equals(user.getUserPassword(), PasswordUtil.generateMD5(newPassword))){
                    user.setUserPassword(newPassword);
                    userDAO.saveUser(user);
                }else{
                    // senha não é diferente
                }
            }catch (Exception exception){
                exception.printStackTrace();
            }
        }
    }

    public UserModel getUser(String email){
        return userDAO.getByEmail(email);
    }
}
