package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        mainJBDC();
    }


    private static void mainJBDC() {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        User user1 = new User("Sergey","Loktionov",(byte)27);
        User user2 = new User("Ivan","Ivanov",(byte)22);
        User user3 = new User("Petr","Petrov",(byte)23);
        User user4 = new User("Nikolay","Nikolayev",(byte)24);

        List<User> userList = List.of(user1,user2,user3,user4);

        for(User user : userList) {
            userService.saveUser(user.getName(),user.getLastName(),user.getAge());
            System.out.println("User с именем " + user.getName() + " добавлен в базу данных");
        }

        userService.getAllUsers().forEach(System.out::println);
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
