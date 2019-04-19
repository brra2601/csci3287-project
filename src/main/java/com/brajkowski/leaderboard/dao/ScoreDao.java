package com.brajkowski.leaderboard.dao;

import java.util.List;

import com.brajkowski.leaderboard.domain.Score;

import org.springframework.stereotype.Repository;

@Repository
public interface ScoreDao {
    public List<Score> getAllScores();
}