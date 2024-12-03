package ru.alishev.springcourse.FirstRestApp.service;

import ru.alishev.springcourse.FirstRestApp.model.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User getUserById(Long userId);

    void addUser(User user);

    void updateUser(Long id, User user);

    void deleteUser(Long userId);

    User findByEmail(String email);

    boolean existsById(Long userId);

//    List<User> getAllUsers();
//
//    User getUserById(Long id);
//
//    void addUser(User user);
//
//    void updateUser(User updatedUser);
//
//    void deleteUser(User user);
//
//    User findByEmail(String email);
//
//    boolean existsById(Long userId);

}