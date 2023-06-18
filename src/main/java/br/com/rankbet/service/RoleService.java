package br.com.rankbet.service;

import br.com.rankbet.dao.RoleDAO;
import br.com.rankbet.model.RoleModel;

import java.util.Optional;

public class RoleService {

    private static RoleDAO roleDAO = new RoleDAO();

    public Optional<RoleModel> getSubscription(long userId){
        return Optional.ofNullable( roleDAO.findById(userId));
    }

}
