package com.brajkowski.leaderboard.dao;

import java.util.List;

import com.brajkowski.leaderboard.domain.Score;

import org.springframework.stereotype.Repository;

@Repository
public interface ScoreDao {
    public List<Score> getAllScores();
    public DaoResult addScore(Score score);
    public List<Score> getHighScoresByLevel(int levelId);
    public List<Score> getHighScoresByLevel(int levelId, int limit);
    public List<Score> getHighScoresByLevelUserlist(int levelId, List<String> userList);
    public List<Score> getHighScoresByLevelUserlist(int levelId, List<String> userList, int limit);
    public List<Score> getHighScoresByLevelAfterDatetime(int levelId, String datetime);
    public List<Score> getHighScoresByLevelAfterDatetime(int levelId, String datetime, int limit);
}