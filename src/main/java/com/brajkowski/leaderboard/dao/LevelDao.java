package com.brajkowski.leaderboard.dao;

import java.util.List;
import java.util.Optional;

import com.brajkowski.leaderboard.domain.Level;

import org.springframework.stereotype.Repository;

@Repository
public interface LevelDao {
    List<Level> getAllLevels();
    Optional<Level> getLevelById(int id);
    Boolean existsById(int id);
}