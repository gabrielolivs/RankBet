package br.com.rankbet.dao;

import br.com.rankbet.model.UserModel;
import br.com.rankbet.service.Manager;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.time.LocalDateTime;

public class LoginDAO {

    private final EntityManager entityManager;

    public LoginDAO(){
        entityManager = Manager.getInstance().getEntityManager();
    }
    private static UserDAO userDAO;

    public boolean authenticate(String email, String password) {
        entityManager.getTransaction().begin();
        try {
            UserModel userModel = userDAO.getByEmail(email);
            if (userModel.getUserPassword().equals(password)) {
                return true; // Autenticação bem-sucedida
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return false; // Autenticação falhou
    }
}
