package ru.alishev.springcourse.FirstRestApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.alishev.springcourse.FirstRestApp.exception.UserErrorResponse;
import ru.alishev.springcourse.FirstRestApp.exception.UserNotFoundException;
import ru.alishev.springcourse.FirstRestApp.models.Role;
import ru.alishev.springcourse.FirstRestApp.models.User;
import ru.alishev.springcourse.FirstRestApp.services.RoleService;
import ru.alishev.springcourse.FirstRestApp.services.UserServiceImpl;


import javax.persistence.EntityNotFoundException;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private UserServiceImpl userService;
    private RoleService roleService;

    @Autowired
    public AdminController(UserServiceImpl userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/currentUser")
    public ResponseEntity<User> getCurrentUser(Principal principal) {
        User currentUser = userService.findByEmail(principal.getName());
        return new ResponseEntity<>(currentUser, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<User> getUserById(@RequestParam(value = "id") Long id) {
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<UserErrorResponse> handleException(UserNotFoundException e) {
        UserErrorResponse userErrorResponse = new UserErrorResponse(
                "Person with this ID wasn't found!",
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(userErrorResponse, HttpStatus.NOT_FOUND);
    }

    @GetMapping("users/roles")
    public ResponseEntity<List<Role>> getAllRoles() {
        return new ResponseEntity<>(roleService.getRoles(), HttpStatus.OK);
    }

    @PostMapping("/users")
    public ResponseEntity<?> addUser(@RequestBody User user, BindingResult result) {
//        if (result.hasErrors()) {
//            Map<String, String> errors = new HashMap<>();
//            result.getFieldErrors().forEach(error ->
//                    errors.put(error.getField(), error.getDefaultMessage())
//            );
//            return ResponseEntity.badRequest().body(errors);
//        }
//        try {
//            userService.addUser(user);
//        } catch (DataIntegrityViolationException e) {
//            result.rejectValue("email", "error.user", "Учетная запись для этого Email уже существует.");
//            Map<String, String> errors = new HashMap<>();
//            result.getFieldErrors().forEach(error ->
//                    errors.put(error.getField(), error.getDefaultMessage())
//            );
//            return ResponseEntity.badRequest().body(errors);
//        }
        userService.addUser(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PutMapping("/edit")
    public ResponseEntity<?> editUser(@RequestBody User user, BindingResult result) {
//        if (bindingResult.hasErrors()) {
//            model.addAttribute("roles", roleService.getRoles());
//            return "edit";
//        }
//        try {
//            userService.updateUser(user);
//        } catch (DataIntegrityViolationException e) {
//            bindingResult.rejectValue("email", "error.user", "Учетная запись для Email уже существует.");
//            model.addAttribute("roles", roleService.getRoles());
//            return "edit";
//        }
//        redirectAttributes.addFlashAttribute("success", "User updated successfully!");
        userService.updateUser(user);
        return ResponseEntity.ok(user);
    }



    @DeleteMapping("/delete")
    public ResponseEntity<HttpStatus> deleteUser(@RequestParam("id") Long id) {
        userService.deleteUser(userService.getUserById(id));
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
