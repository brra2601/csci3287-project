package com.brajkowski.leaderboard.repository;

import java.util.List;
import java.util.Optional;

import com.brajkowski.leaderboard.domain.Level;

import org.springframework.data.repository.CrudRepository;

public interface LevelRepository extends CrudRepository<Level, Integer> {
    @Override
    List<Level> findAll();

    @Override
    Optional<Level> findById(Integer id);
}