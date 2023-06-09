package br.com.rankbet.dao;

import br.com.rankbet.model.UserModel;
import br.com.rankbet.service.Manager;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

public class UserDAO {

    private final EntityManager entityManager;

    public UserDAO(){
        entityManager = Manager.getInstance().getEntityManager();
    }

    public void cadastrar(UserModel userModel) {
        entityManager.getTransaction().begin();
        entityManager.persist(userModel);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void remover(UserModel userModel){
        entityManager.getTransaction().begin();
        entityManager.remove(userModel);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public UserModel getByEmail(String email){
        UserModel usuario = null;
        entityManager.getTransaction().begin();
        try {
            String hql = "FROM Usuario WHERE email = :email";
            Query query = entityManager.createQuery(hql, UserModel.class);
            query.setParameter("email", email);
            usuario = (UserModel) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return usuario;
    }

}
