package br.com.rankbet.hibernate;

import br.com.rankbet.hibernate.interceptor.Interceptor;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public final class HibernateSessionFactory {

    private HibernateSessionFactory() {

    }

    private static final SessionFactory sessionFactory;

    static {
        StandardServiceRegistry standardRegistry = new
                StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml")
                .build();
        Metadata metaData = new MetadataSources(
                standardRegistry)
                .getMetadataBuilder()
                .build();
        sessionFactory = metaData.getSessionFactoryBuilder().applyInterceptor(new Interceptor()).build();
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
