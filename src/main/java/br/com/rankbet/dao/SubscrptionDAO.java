package br.com.rankbet.dao;

import br.com.rankbet.model.SubscriptionModel;
import br.com.rankbet.service.Manager;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

public class SubscrptionDAO {

    private final EntityManager entityManager;

    public SubscrptionDAO(){
        entityManager = Manager.getInstance().getEntityManager();
    }


    public SubscriptionModel getSubscription(long userId) {
        SubscriptionModel subscriptionModel;
        entityManager.getTransaction().begin();
        try {
            String hql = "FROM TBL_SUBSCRIPTION WHERE USER_ID = :userId";
            Query query = entityManager.createQuery(hql, SubscriptionModel.class);
            query.setParameter("user_id", userId);
            subscriptionModel = (SubscriptionModel) query.getSingleResult();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }finally {
            entityManager.close();
        }
        return subscriptionModel;
    }
}
