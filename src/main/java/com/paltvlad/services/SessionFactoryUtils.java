package com.paltvlad.services;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;

@Component
public class SessionFactoryUtils {

    private final SessionFactory factory = new Configuration()
            .configure("hibernate.cfg.xml")
            .buildSessionFactory();


    public Session getSession() {
        return factory.getCurrentSession();
    }


    public void shutdown() {
        if (factory != null) {
            factory.close();
        }
    }

}
