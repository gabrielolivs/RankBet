package br.com.rankbet.dao;

import br.com.rankbet.model.RoleModel;
import br.com.rankbet.service.Manager;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.Optional;

public class RoleDAO {
    private final EntityManager entityManager;

    public RoleDAO(){
        entityManager = Manager.getInstance().getEntityManager();
    }

    public Optional<RoleModel> getRole(long roleId) {
        Optional<RoleModel> roleModel;
        entityManager.getTransaction().begin();
        try {
            String hql = "FROM TBL_ROLE WHERE id = :roleId";
            Query query = entityManager.createQuery(hql, RoleModel.class);
            query.setParameter("id", roleId);
            roleModel = (Optional<RoleModel>) query.getSingleResult();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }finally {
            entityManager.close();
        }
        return roleModel;
    }
}

