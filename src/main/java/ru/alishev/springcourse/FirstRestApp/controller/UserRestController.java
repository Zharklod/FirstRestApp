package ru.alishev.springcourse.FirstRestApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.alishev.springcourse.FirstRestApp.model.User;
import ru.alishev.springcourse.FirstRestApp.service.UserService;

import java.security.Principal;

@RestController
@RequestMapping("/user")
public class UserRestController {

    private final UserService userService;

    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/profile_user")
    public User getUserProfile(Principal principal) {
        return userService.findByEmail(principal.getName());
    }
}
