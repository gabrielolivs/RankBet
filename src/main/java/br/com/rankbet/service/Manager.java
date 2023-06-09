package br.com.rankbet.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Manager {
    private Manager(){
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("YourPersistenceUnitName");
        entityManager = factory.createEntityManager();
    }
    private EntityManager entityManager;
    private static Manager instance = null;

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public static Manager getInstance() {
        if(instance == null)
            instance = new Manager();
        return instance;
    }
}
