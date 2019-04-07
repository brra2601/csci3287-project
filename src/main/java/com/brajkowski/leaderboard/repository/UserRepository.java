package com.brajkowski.leaderboard.repository;

import java.util.List;
import java.util.Optional;

import com.brajkowski.leaderboard.domain.User;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {
    @Override
    public List<User> findAll();

    public Optional<User> findByEmail(String email);
    public boolean existsByEmail(String email);
}