package br.com.rankbet.dao;

import br.com.rankbet.dao.base.BaseDao;
import br.com.rankbet.model.RoleModel;
import br.com.rankbet.model.SubscriptionModel;
import br.com.rankbet.model.UserModel;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RoleDAO extends BaseDao<RoleModel> {
    public RoleDAO(){super(RoleModel.class);}


    public SubscriptionModel getSubscription(long userId) {
        return  new SubscriptionModel();
    }

    public RoleModel findByTypeName(String roleName){
        CriteriaBuilder criteriaBuilder = this.getSession().getCriteriaBuilder();
        CriteriaQuery<RoleModel> criteriaQuery = criteriaBuilder.createQuery(persistentClass);
        Root<RoleModel> from = criteriaQuery.from(persistentClass);

        List<Predicate> predicateList = new ArrayList<>();
        predicateList.add(criteriaBuilder.equal(from.get("typeName"), roleName));

        criteriaQuery.where(predicateList.toArray(new Predicate[0]));

        TypedQuery<RoleModel> q = this.getSession().createQuery(criteriaQuery);
        return q.getSingleResult();
    }
}

