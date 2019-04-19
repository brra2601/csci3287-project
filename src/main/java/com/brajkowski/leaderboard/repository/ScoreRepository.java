package com.brajkowski.leaderboard.repository;

import java.util.List;

import com.brajkowski.leaderboard.domain.Score;

import org.springframework.data.repository.CrudRepository;

public interface ScoreRepository extends CrudRepository<Score, Integer> {
    @Override
    public List<Score> findAll();
}