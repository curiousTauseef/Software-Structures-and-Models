/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wontheone.hiber01;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author Won Seob Seo <Wons at Metropolia UAS>
 */
public class HibernateUtil {
    
    private static final HibernateUtil instance = new HibernateUtil();

    private SessionFactory sessionFactory;

    private HibernateUtil(){
        try {
            // Create the SessionFactory from standard (hibernate.cfg.xml) 
            // config file.
            Configuration config = new Configuration();
            config.configure("hibernate.cfg.xml");
            new SchemaExport(config).create(true, true);
            StandardServiceRegistryBuilder serviceRegistryBuilder
                    = new StandardServiceRegistryBuilder();
            serviceRegistryBuilder.applySettings(config.getProperties());
            ServiceRegistry serviceRegistry = serviceRegistryBuilder.build();
            sessionFactory = config.buildSessionFactory(serviceRegistry);
            // above is peter
            Session session = sessionFactory.openSession();
            Transaction t = session.beginTransaction();
            Person p = new Person(); 
            p.setName("sonoo");
            session.saveOrUpdate(p);
            session.getTransaction().commit(); 
        } catch (Throwable ex) {
            // Log the exception. 
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static synchronized SessionFactory getSessionFactory() {
        return instance.sessionFactory;
    }
}
