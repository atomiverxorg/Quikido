package com.quikido.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.quikido.auth.model.User;
import com.quikido.auth.repository.UserRepository;

@Service
public class UserService {
    @Autowired(required=true)
    private UserRepository userRepository;

    public void registerUser(User user) {
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
        userRepository.save(user);
    }
}
