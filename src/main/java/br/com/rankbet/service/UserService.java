package br.com.rankbet.service;

import br.com.rankbet.dao.UserDAO;
import br.com.rankbet.exception.BusinessException;
import br.com.rankbet.model.UserModel;
import br.com.rankbet.model.dto.UserDTO;
import br.com.rankbet.utils.PasswordUtil;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.util.Objects;

public class UserService {

    private UserDAO userDAO = new UserDAO();

    public boolean registerUser(UserDTO userDTO) throws InvocationTargetException, IllegalAccessException {
        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(userModel, userDTO);
        if(!userModel.getEmail().isEmpty()){
            try {
                String md5Password = PasswordUtil.generateMD5(userModel.getUserPassword());
                userModel.setUserPassword(md5Password);
                userModel.setUserEnabled(1L);
                userModel.setCreateAt(LocalDateTime.now());
                userModel.setUpdatedAt(LocalDateTime.now());
                 userDAO.save(userModel);
                 return true;
            }catch (Exception exception){
                exception.printStackTrace();
                return false;
            }
        }
        return false;
    }

    public boolean updateUser(UserDTO userDTO) throws InvocationTargetException, IllegalAccessException {
        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(userModel, userDTO);
        try {
            userDAO.save(userModel);
            return true;
        } catch (BusinessException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void updatePasswordUser(String email, String newPassword){
        if(email != null && newPassword != null){
            try{
                UserModel user = userDAO.findById(1);
                if(!Objects.equals(user.getUserPassword(), PasswordUtil.generateMD5(newPassword))){
                    user.setUserPassword(newPassword);
                    userDAO.save(user);
                }else{
                    // senha não é diferente
                }
            }catch (Exception exception){
                exception.printStackTrace();
            }
        }
    }

    public UserModel getUser(String email){
        return userDAO.findByEmail(email);
    }
}
