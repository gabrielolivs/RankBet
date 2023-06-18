package br.com.rankbet.service;

import br.com.rankbet.dao.SubscrptionDAO;
import br.com.rankbet.model.SubscriptionModel;

public class SubscriptionService {
    private static SubscrptionDAO subscrption = new SubscrptionDAO();

    public SubscriptionModel getSubscription(long userId){
        return subscrption.findByUser(userId);
    }

}