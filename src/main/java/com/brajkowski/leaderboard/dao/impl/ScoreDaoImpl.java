package com.brajkowski.leaderboard.dao.impl;

import java.util.List;

import com.brajkowski.leaderboard.dao.DaoResult;
import com.brajkowski.leaderboard.dao.ScoreDao;
import com.brajkowski.leaderboard.domain.Score;
import com.brajkowski.leaderboard.repository.ScoreRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ScoreDaoImpl implements ScoreDao {

    @Autowired
    private ScoreRepository scoreRepository;
    
    @Override
    public List<Score> getAllScores() {
        return scoreRepository.findAll();
    }

    @Override
    public DaoResult addScore(Score score) {
        scoreRepository.save(score);
        return new DaoResult(true, null);
    }

}