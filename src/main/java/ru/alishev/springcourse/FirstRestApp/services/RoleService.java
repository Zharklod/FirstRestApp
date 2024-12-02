package ru.alishev.springcourse.FirstRestApp.services;

import org.springframework.stereotype.Service;
import ru.alishev.springcourse.FirstRestApp.models.Role;

import java.util.List;

@Service
public interface RoleService {
    List<Role> getRoles();
}
