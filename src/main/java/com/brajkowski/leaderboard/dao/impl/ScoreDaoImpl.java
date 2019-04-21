package com.brajkowski.leaderboard.dao.impl;

import java.util.List;
import java.util.Optional;

import com.brajkowski.leaderboard.dao.DaoResult;
import com.brajkowski.leaderboard.dao.ScoreDao;
import com.brajkowski.leaderboard.domain.Score;
import com.brajkowski.leaderboard.repository.LevelRepository;
import com.brajkowski.leaderboard.repository.ScoreRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ScoreDaoImpl implements ScoreDao {

    @Autowired
    private ScoreRepository scoreRepository;

    @Autowired
    private LevelRepository LevelRepository;
    
    @Override
    public List<Score> getAllScores() {
        return scoreRepository.findAll();
    }

    @Override
    public DaoResult addScore(Score score) {
        if (LevelRepository.existsById(score.level_id)) {
            scoreRepository.save(score);
            return new DaoResult(true, Optional.of("Successfully added score"));
        }
        return new DaoResult(false, Optional.of("Level does not exist"));
        
    }

    @Override
    public List<Score> getHighScoresByLevel(int levelId) {
        return scoreRepository.findHighScoresByLevel(levelId);
    }

    @Override
    public List<Score> getHighScoresByLevel(int levelId, int limit) {
        return scoreRepository.findHighScoresByLevel(levelId, limit);
    }

    @Override
    public List<Score> getHighScoresByLevelUserlist(int levelId, List<String> userList) {
        return scoreRepository.findHighScoresByLevelUserlist(levelId, userList);
    }

    @Override
    public List<Score> getHighScoresByLevelUserlist(int levelId, List<String> userList, int limit) {
        return scoreRepository.findHighScoresByLevelUserlist(levelId, userList, limit);
    }

    @Override
    public List<Score> getHighScoresByLevelAfterDatetime(int levelId, String datetime) {
        return scoreRepository.findHighScoresByLevelAfterDatetime(levelId, datetime);
    }

    @Override
    public List<Score> getHighScoresByLevelAfterDatetime(int levelId, String datetime, int limit) {
        return scoreRepository.findHighScoresByLevelAfterDatetime(levelId, datetime, limit);
    }
}