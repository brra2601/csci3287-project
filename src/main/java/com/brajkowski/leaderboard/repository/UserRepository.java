package com.brajkowski.leaderboard.repository;

import com.brajkowski.leaderboard.domain.User;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {
    
}