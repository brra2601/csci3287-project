package com.brajkowski.leaderboard.dao.impl;

import java.util.List;
import java.util.Optional;

import com.brajkowski.leaderboard.dao.UserDao;
import com.brajkowski.leaderboard.dao.DaoResult;
import com.brajkowski.leaderboard.domain.User;
import com.brajkowski.leaderboard.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserDaoImpl implements UserDao {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserByUsername(String username) {
        return userRepository.findById(username);
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public DaoResult addUser(User user) {
        if (userRepository.existsById(user.username)) {
            String message = String.format("A user already exists with the username %s", user.username);
            return new DaoResult(false, Optional.of(message));
        }
        if (userRepository.existsByEmail(user.email)) {
            String message = String.format("A user already exists with the email %s", user.email);
            return new DaoResult(false, Optional.of(message));
        }
        String encodedPassword = passwordEncoder.encode(user.password);
        user.password = encodedPassword;
        userRepository.save(user);
        return new DaoResult(true, Optional.of("User added"));
    }

}