package com.spring.service;

import com.spring.model.User;

public interface UserService {
    User save(User user);
    User findByEmail(String email);
}
