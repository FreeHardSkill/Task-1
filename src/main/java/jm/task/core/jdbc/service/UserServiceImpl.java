package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {
    //private final UserDao USER_JDBC = new UserDaoJDBCImpl();
    private final UserDao USER_HIBER = new UserDaoHibernateImpl();

    public void createUsersTable() {
        USER_HIBER.createUsersTable();
    }

    public void dropUsersTable() {
        USER_HIBER.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        USER_HIBER.saveUser(name,lastName,age);
    }

    public void removeUserById(long id) {
        USER_HIBER.removeUserById(id);
    }

    public List<User> getAllUsers() {
        return USER_HIBER.getAllUsers();
    }

    public void cleanUsersTable() {
        USER_HIBER.cleanUsersTable();
    }
}
