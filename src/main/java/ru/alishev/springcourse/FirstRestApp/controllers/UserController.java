package ru.alishev.springcourse.FirstRestApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.alishev.springcourse.FirstRestApp.models.User;
import ru.alishev.springcourse.FirstRestApp.repositories.UserRepository;
import ru.alishev.springcourse.FirstRestApp.services.UserService;
import ru.alishev.springcourse.FirstRestApp.services.UserServiceImpl;

import java.security.Principal;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/profile_user")
    public User getUserProfile(Principal principal) {
        return userService.findByEmail(principal.getName());
    }
}
