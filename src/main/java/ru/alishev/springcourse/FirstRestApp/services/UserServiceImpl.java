package ru.alishev.springcourse.FirstRestApp.services;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.alishev.springcourse.FirstRestApp.exception.UserNotFoundException;
import ru.alishev.springcourse.FirstRestApp.models.User;
import ru.alishev.springcourse.FirstRestApp.repositories.UserRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
//public class UserServiceImpl implements UserService, UserDetailsService {
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
//    private PasswordEncoder passwordEncoder;

//    @Autowired
//    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
//        this.userRepository = userRepository;
//        this.passwordEncoder = passwordEncoder;
//    }

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        User user = null;
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            user = optionalUser.get();
        } else {
            return optionalUser.orElseThrow(UserNotFoundException::new);
        }
        return user;
    }

    @Override
    public void addUser(User user) {
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public void updateUser(User user) {
//        User oldUser = getUserById(user.getId());
//        if (!user.getPassword().equals(oldUser.getPassword())) {
//            user.setPassword(passwordEncoder.encode(user.getPassword()));
//        }
        userRepository.save(user);
    }

//    @Override
//    public void updateUser(Long id, User updatedUser) {
//        Optional<User> userFromDb = userRepository.findById(id);
//        if (userFromDb.isPresent()) {
//            User existingUser = userFromDb.get();
//
//            // Обновляем остальные поля пользователя
//            existingUser.setFirstName(updatedUser.getFirstName());
//            existingUser.setLastName(updatedUser.getLastName());
//            existingUser.setEmail(updatedUser.getEmail());
//            existingUser.setAge(updatedUser.getAge());
//
//            // Обновляем роли пользователя
//            existingUser.setRoles(updatedUser.getRoles());
//
////            // Обновляем пароль, если он был изменен
////            if (updatedUser.getPassword() != null && !updatedUser.getPassword().isEmpty()) {
////                existingUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
////            }
//
//            userRepository.save(existingUser);
//        }
//    }

    @Override
    public void deleteUser(User user) {
        userRepository.deleteById(user.getId());
    }

//    @Override
//    @Transactional(readOnly = true)
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        User user = findByEmail(email);
//        if (user == null) {
//            throw new UsernameNotFoundException(String.format("No user found with username '%s'", email));
//        }
//        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthorities(user.getRoles()));
//
//    }
//
    @Transactional
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
//
//    private Collection<? extends GrantedAuthority> getAuthorities(Collection<Role> roles) {
//        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getRoleName())).collect(Collectors.toList());
//    }
}

