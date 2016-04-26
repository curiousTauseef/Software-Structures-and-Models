/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wontheone.hiber01;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Won Seob Seo <Wons at Metropolia UAS>
 */
public class DataProvider {

    private static final DataProvider INSTANCE = new DataProvider();

    private DataProvider() {

    }

    public static DataProvider getInstance() {
        return INSTANCE;
    }

    public static Person getPersonByName(String name) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        List<Person> persons = null;
        try {
            tx = session.beginTransaction();
            Criteria criteria = session.createCriteria(Person.class);
            criteria.add(Restrictions.eq("name", name));
            persons = (ArrayList<Person>) criteria.list();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return persons.get(0);
    }

    public static List<Car> getCars() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        List<Car> listCar = null;
        try {
            tx = session.beginTransaction();
            Criteria criteria = session.createCriteria(Car.class);
            listCar = (List<Car>) criteria.list();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return listCar;
    }

    public static List<Car> getCarsByPersonName(String name) {
        return (List<Car>) getPersonByName(name).getCars();
    }

    public static List<Car> getCarsByLessThanPrice(double price) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        List<Car> listCar = null;
        try {
            tx = session.beginTransaction();
            Criteria criteria = session.createCriteria(Car.class);
            criteria.add(Restrictions.lt("price", price));
            listCar = (List<Car>) criteria.list();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return listCar;
    }

//    public static List<Car> getCarsByPerson(Person p) {
//        Session session = HibernateUtil.getSessionFactory().openSession();
//        Transaction tx = null;
//        List<Car> listCar = null;
//        try {
//            tx = session.beginTransaction();
//            Query query = session.getNamedQuery("FindAll");
//            listCar = (List<Car>) query.list();
//            tx.commit();
//        } catch (Exception e) {
//            if (tx != null) {
//                tx.rollback();
//            }
//            e.printStackTrace();
//        } finally {
//            session.close();
//        }
//        return listCar;
//    }
}
