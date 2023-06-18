package br.com.rankbet.dao;

import br.com.rankbet.model.UserModel;
import br.com.rankbet.service.Manager;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.time.LocalDateTime;

public class UserDAO {

    private final EntityManager entityManager;

    public UserDAO(){
        entityManager = Manager.getInstance().getEntityManager();
    }

    public void saveUser(UserModel userModel) {
        entityManager.getTransaction().begin();
        userModel.setCreateAt(LocalDateTime.now());
        userModel.setUpdatedAt(LocalDateTime.now());
        //verificar se atualiza somente os campos diferentes do que esta salvo na base ou diferentes de null
        entityManager.persist(userModel);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void deleteUser(UserModel userModel){
        entityManager.getTransaction().begin();
        entityManager.remove(userModel);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public UserModel getByEmail(String email){
        UserModel usuario = null;
        entityManager.getTransaction().begin();
        try {
            String hql = "FROM TBL_USER WHERE email = :email";
            Query query = entityManager.createQuery(hql, UserModel.class);
            query.setParameter("email", email);
            usuario = (UserModel) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            entityManager.close();
        }
        return usuario;
    }

    public boolean existsEmail(String email) {
        String existEmail;
        entityManager.getTransaction().begin();
        try {
            String hql = "FROM Usuario WHERE email = :email";
            Query query = entityManager.createQuery(hql, UserModel.class);
            query.setParameter("email", email);
            existEmail = String.valueOf(query.getFirstResult());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            entityManager.close();
        }
        return !existEmail.isEmpty();
    }

}
