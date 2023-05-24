package ru.alphalab.testtask.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.alphalab.testtask.hibernate.models.Document;
import ru.alphalab.testtask.hibernate.models.Person;

/**
 * @author Ilya Avkhimenya
 */
public class HibernateUtil {
    private static final SessionFactory sessionFactory;

    static {
        try {
            Configuration configuration = new HibernateConfig();
            configuration.addAnnotatedClass(Person.class);
            configuration.addAnnotatedClass(Document.class);
            sessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create database connection");
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
