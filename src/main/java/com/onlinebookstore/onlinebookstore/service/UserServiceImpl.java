package com.onlinebookstore.onlinebookstore.service;

import com.onlinebookstore.onlinebookstore.config.CustomPasswordEncoder;
import com.onlinebookstore.onlinebookstore.entities.Role;
import com.onlinebookstore.onlinebookstore.entities.RoleName;
import com.onlinebookstore.onlinebookstore.entities.User;
import com.onlinebookstore.onlinebookstore.exception.UserNotFoundException;
import com.onlinebookstore.onlinebookstore.repository.RoleRepository;
import com.onlinebookstore.onlinebookstore.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {


    private UserRepository userRepository;
    private RoleRepository roleRepository;


    private CustomPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, CustomPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public User signUp(User user, Set<String> roleNames) {

        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("Email is already in use!");
        }

        user.setPassword(passwordEncoder.encodePassword(user.getPassword()));

        // Set user roles
        Set<Role> roles = new HashSet<>();
        for (String roleName : roleNames) {
            Role role = roleRepository.findByName(RoleName.valueOf(roleName))
                    .orElseThrow(() -> new RuntimeException("Role not found: " + roleName));
            roles.add(role);
        }
        user.setRoles(roles);


        return userRepository.save(user);

    }


    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
    }


}
