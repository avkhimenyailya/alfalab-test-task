package ru.alphalab.testtask.hibernate;

import org.hibernate.cfg.Configuration;

/**
 * @author Ilya Avkhimenya
 */
public class HibernateConfig extends Configuration {
    private static final String dbUrl = "jdbc:postgresql://localhost:5432/al_test";
    private static final String username = "postgres";
    private static final String password = "";

    public HibernateConfig() {
        setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");

        setProperty("hibernate.connection.url", dbUrl);
        setProperty("hibernate.connection.username", username);
        setProperty("hibernate.connection.password", password);

        setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        setProperty("hibernate.show_sql", "true");
        setProperty("hibernate.hbm2ddl.auto", "update");
    }
}
