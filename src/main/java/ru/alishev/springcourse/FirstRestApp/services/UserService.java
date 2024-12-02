package ru.alishev.springcourse.FirstRestApp.services;

import ru.alishev.springcourse.FirstRestApp.models.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User getUserById(Long id);

    void addUser(User user);

    void updateUser(User updatedUser);

    void deleteUser(User user);
}
