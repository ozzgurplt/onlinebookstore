package com.onlinebookstore.onlinebookstore.service;

import com.onlinebookstore.onlinebookstore.entities.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface UserService {
    User signUp(User user, Set<String> roleNames);


    User getUserById(Long userId);

}
