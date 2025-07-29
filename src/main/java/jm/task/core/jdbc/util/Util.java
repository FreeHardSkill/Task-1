package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import jm.task.core.jdbc.model.User;

public class Util {
    // реализуйте настройку соеденения с БД
    private static String url = "jdbc:postgresql://localhost:5432/task_1";
    private static String username = "postgres";
    private static String password = "postgres";


    public static Connection open() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static SessionFactory sessionFactory() {
        Configuration configuration = new Configuration();
        configuration
                .addAnnotatedClass(User.class)
                .addPackage("jm.task.core.jdbc")
                .setProperty("hibernate.connection.driver_class", "org.postgresql.Driver")
                .setProperty("hibernate.connection.url", url)
                .setProperty("hibernate.connection.username", username)
                .setProperty("hibernate.connection.password", password)
                .setProperty("hibernate.hbm2ddl.auto", "update");

        return configuration.buildSessionFactory();
    }
}
