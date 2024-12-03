package ru.alishev.springcourse.FirstRestApp.service;

import ru.alishev.springcourse.FirstRestApp.model.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {

    void saveRole(Role role);

    Optional<Role> findRoleById(Long id);

    List<Role> getRoles();
}