package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    private static final UserService userService = new UserServiceImpl();
    public static void main(String[] args) {
        userService.createUsersTable();

        userService.saveUser("Gadya","Petrovich", (byte) 5);
        userService.saveUser("Vasya","Pupkin", (byte) 15);
        userService.saveUser("Leo","Tolstoy", (byte) 99);
        userService.saveUser("Chingachkuk","BigSnake", (byte) 18);

        userService.getAllUsers().forEach(System.out::println);

        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
