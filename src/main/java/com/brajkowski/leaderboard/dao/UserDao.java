package com.brajkowski.leaderboard.dao;

import java.util.List;
import java.util.Optional;

import com.brajkowski.leaderboard.dao.DaoResult;
import com.brajkowski.leaderboard.domain.User;

import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {
    public List<User> getAllUsers();
    public Optional<User> getUserByUsername(String username);
    public Optional<User> getUserByEmail(String email);
    public DaoResult addUser(User user);
}