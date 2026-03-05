package com.klu;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
public class HibernateUtil {
    private static SessionFactory factory;
    static {
        try {
            factory = new Configuration().configure().buildSessionFactory();
            System.out.println("Hibernate SessionFactory Created");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static SessionFactory getSessionFactory() {
        return factory;
    }
}
