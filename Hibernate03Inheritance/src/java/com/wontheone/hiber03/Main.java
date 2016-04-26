/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wontheone.hiber03;

import org.hibernate.Session;

/**
 *
 * @author Won Seob Seo <Wons at Metropolia UAS>
 */
public class Main {

    public static void main(String[] args) {

        Person p = new Person();
        p.setName("Pekka");

        Person p1 = new Person();
        p1.setName("Ilpo");

        Car hyundai = new Car();
        hyundai.setOwner(p);
        hyundai.setPrice(2000.6);

        Car kia = new Car();
        kia.setOwner(p);
        kia.setPrice(1800.3);

        p.getCars().add(hyundai);
        p.getCars().add(kia);

        // Student st = new Person();
        // Student s = new Person(); ?? Person object doesn't have the memory allcated for Student member variable
        Student st = new Student();
        st.setName("Jedai");
        st.setStudentNumber("1571231");

        UASstudent uasSt = new UASstudent();
        uasSt.setName("Kerrigan");
        uasSt.setStudentNumber("1775377");
        uasSt.setMajor("Of Course IT");

        FlyingCar flyingCar = new FlyingCar();
        flyingCar.setOwner(p1);
        flyingCar.setPrice(2226);
        flyingCar.setAltitude(222.3);
        p1.getCars().add(flyingCar);

        JetCar jetCar = new JetCar();
        jetCar.setOwner(p);
        jetCar.setPrice(12555);
        jetCar.setAltitude(455.3);
        jetCar.setBoostSpeed(44445);
        p.getCars().add(jetCar);
        
        Animal anim = new Animal();
        anim.setName("Herbivous");
        
        Mammal mammal = new Mammal();
        mammal.setName("Whale");
        mammal.setSpineLength("3m");
        
        Dog dog = new Dog();
        dog.setName("Shepherd");
        dog.setSpineLength("50cm");
        dog.setBarking("Arf");

        Session session
                = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.saveOrUpdate(p);
        session.saveOrUpdate(p1);
        session.saveOrUpdate(kia);
        session.saveOrUpdate(hyundai); // save changes in object graph starting at t
        // save changes in object graph starting at u
        session.saveOrUpdate(st);
        session.saveOrUpdate(uasSt);
        session.saveOrUpdate(flyingCar);
        session.saveOrUpdate(jetCar);
        session.saveOrUpdate(anim);
        session.saveOrUpdate(mammal);
        session.saveOrUpdate(dog);
        session.getTransaction().commit();
        session.close();
    }

}
