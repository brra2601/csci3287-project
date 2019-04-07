package com.brajkowski.leaderboard.rest;

import com.brajkowski.leaderboard.repository.UserRepository;
import com.brajkowski.leaderboard.dao.UserDao;
import com.brajkowski.leaderboard.domain.DaoCreationResult;
import com.brajkowski.leaderboard.domain.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/user")
public class UserApi {
    @Autowired
    private UserDao users;

    @GetMapping
    public ResponseEntity<Iterable<User>> getAllUsers() {
        return ResponseEntity.ok(users.getAllUsers());
    }

    @PostMapping
    public ResponseEntity<Object> createNewUser(@RequestBody User user) {
        DaoCreationResult result = users.addUser(user);
        if (result.didSucceed()) {
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.badRequest().body(result.getMessage());
    }
}