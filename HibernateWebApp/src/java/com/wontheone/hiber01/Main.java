/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wontheone.hiber01;

import org.hibernate.Session;

/**
 *
 * @author Won Seob Seo <Wons at Metropolia UAS>
 */
public class Main {

    public static void main(String[] args) {

        Person p = new Person();
        p.setName("Pekka");

        Car hyundai = new Car();
        hyundai.setOwner(p);
        hyundai.setPrice(2000.6);
        
        Car kia = new Car();
        kia.setOwner(p);
        kia.setPrice(1800.3);
        
        p.getCars().add(hyundai);
        p.getCars().add(kia);
        
        Session session
                = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.saveOrUpdate(p);
        session.saveOrUpdate(kia); 
        session.saveOrUpdate(hyundai); // save changes in object graph starting at t
        // save changes in object graph starting at u
        session.getTransaction().commit();
        session.close();
    }

}
