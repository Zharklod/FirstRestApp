package ru.alishev.springcourse.FirstRestApp.Initializer;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.alishev.springcourse.FirstRestApp.model.Role;
import ru.alishev.springcourse.FirstRestApp.model.User;
import ru.alishev.springcourse.FirstRestApp.service.RoleService;
import ru.alishev.springcourse.FirstRestApp.service.UserService;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.HashSet;
import java.util.Set;

@Component
public class Init {
    private final RoleService roleService;
    private final UserService userService;
    private User user1;
    private User user2;

    @Autowired
    public Init(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @PostConstruct
    private void init() {
        roleService.saveRole(new Role(1L, "ROLE_ADMIN"));
        roleService.saveRole(new Role(2L, "ROLE_USER"));

        Set<Role> roles1 = new HashSet<>();
        roles1.add(roleService.findRoleById(1L).get());
        roles1.add(roleService.findRoleById(2L).get());

        Set<Role> roles2 = new HashSet<>();
        roles2.add(roleService.findRoleById(2L).get());

        userService.addUser(new User("admin", "admin", 33, "admin@gmail.com", "admin", roles1));
        userService.addUser(new User("user", "user", 44, "user@gmail.com", "user", roles2));
    }

    @PreDestroy
    public void destroy() {
        userService.deleteUser(1L);
        userService.deleteUser(2L);
    }
}
