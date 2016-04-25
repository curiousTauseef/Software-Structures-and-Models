
import com.wontheone.hiber01.Car;
import com.wontheone.hiber01.HibernateUtil;
import com.wontheone.hiber01.Person;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.hibernate.Session;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Web application lifecycle listener.
 *
 * @author Won Seob Seo <Wons at Metropolia UAS>
 */
public class StartupListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
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
        Car peugeot = new Car();
        peugeot.setOwner(p1);
        peugeot.setPrice(1250.1);
        
        p.getCars().add(hyundai);
        p.getCars().add(kia);
        p1.getCars().add(peugeot);
        Session session
                = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.saveOrUpdate(p);
        session.saveOrUpdate(p1);
        session.saveOrUpdate(peugeot);
        session.saveOrUpdate(kia); 
        session.saveOrUpdate(hyundai); // save changes in object graph starting at t
        // save changes in object graph starting at u
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("\"StartupListener contextDestroyed()");
        System.out.println(sce);
    }
}
