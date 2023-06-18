package br.com.rankbet.dao;

import br.com.rankbet.dao.base.BaseDao;
import br.com.rankbet.model.SubscriptionModel;

public class SubscrptionDAO extends BaseDao<SubscriptionModel> {

    public SubscrptionDAO(){super(SubscriptionModel.class);}


    public SubscriptionModel getSubscription(long userId) {
        return  new SubscriptionModel();
    }
}
