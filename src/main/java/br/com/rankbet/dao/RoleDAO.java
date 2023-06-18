package br.com.rankbet.dao;

import br.com.rankbet.dao.base.BaseDao;
import br.com.rankbet.model.RoleModel;
import br.com.rankbet.model.SubscriptionModel;

import java.util.Optional;

public class RoleDAO extends BaseDao<RoleModel> {
    public RoleDAO(){super(RoleModel.class);}


    public SubscriptionModel getSubscription(long userId) {
        return  new SubscriptionModel();
    }
}

