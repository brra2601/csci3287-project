package com.brajkowski.leaderboard.service;

import java.util.List;

import com.brajkowski.leaderboard.dao.ScoreDao;
import com.brajkowski.leaderboard.domain.Score;
import com.brajkowski.leaderboard.domain.UsernameList;
import com.mysql.cj.util.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScoreService {
    @Autowired
    private ScoreDao scores;

    @Autowired
    private FriendService friendService;

    public List<Score> getFilteredHighScores(int levelId, String userFilter, String viewFilter, String username) {
        userFilter = userFilter.toLowerCase();
        if (userFilter.compareTo("me") == 0) {
            return scores.getHighScoresByLevelUserlist(levelId, List.of(username));
        }
        else if (userFilter.compareTo("friends") == 0) {
            UsernameList friendList = friendService.generateFriendList(username);
            return scores.getHighScoresByLevelUserlist(levelId, friendList.usernames);
        }
        // TODO: return global scores.
        return null;
    }

    public List<Score> getFilteredHighScores(int levelId, String userFilter, String viewFilter, String username, int limit) {
        return null;
    }
}