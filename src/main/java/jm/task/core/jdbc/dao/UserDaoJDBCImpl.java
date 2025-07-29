package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UserDaoJDBCImpl implements UserDao {


    public void createUsersTable() {
        try(Connection connection = Util.open();
            Statement statement = connection.createStatement()) {
            String query = """
                    CREATE TABLE IF NOT EXISTS "users" (
                            id SERIAL PRIMARY KEY,  name varchar(32), last_name varchar(32), age integer)
                    """;
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try(Connection connection = Util.open();
            Statement statement = connection.createStatement()) {
            String query = """
                        DROP TABLE IF EXISTS "users";
                    """;

            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String query = """
                    INSERT INTO "users" (name,last_name,age) VALUES (?,?,?)
                    """;
        try(Connection connection = Util.open();
            PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1,name);
            statement.setString(2,lastName);
            statement.setByte(3,age);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        String query = """
                    DELETE FROM "users" WHERE id = ?
                    """;
        try(Connection connection = Util.open();
            PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setLong(1,id);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        String query = """
                    SELECT * FROM "users"
                    """;
        List<User> users = new ArrayList<>();

        try(Connection connection = Util.open();
            PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet rs = statement.executeQuery();

            while(rs.next()) {
                User user = new User(rs.getString("name"),rs.getString("last_name"),rs.getByte("age"));
                user.setId(rs.getLong("id"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() {
        String query = """
                    TRUNCATE TABLE "users"
                    """;

        try(Connection connection = Util.open();
            Statement statement = connection.createStatement()) {

            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public UserDaoJDBCImpl() {

    }
}
