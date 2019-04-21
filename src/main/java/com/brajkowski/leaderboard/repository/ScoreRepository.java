package com.brajkowski.leaderboard.repository;

import java.util.List;

import com.brajkowski.leaderboard.domain.Score;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ScoreRepository extends CrudRepository<Score, Integer> {
    @Override
    List<Score> findAll();

    @Query(value = "SELECT * FROM scores WHERE level_id = :level ORDER BY value", nativeQuery = true)
    List<Score> findHighScoresByLevel(@Param("level") int levelId);

    @Query(value = "SELECT * FROM scores WHERE level_id = :level ORDER BY value DESC LIMIT :limit", nativeQuery = true)
    List<Score> findHighScoresByLevelLimit(@Param("level") int levelId, @Param("limit") int limit);
}