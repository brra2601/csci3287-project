package com.brajkowski.leaderboard.service;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import com.brajkowski.leaderboard.dao.ScoreDao;
import com.brajkowski.leaderboard.domain.Score;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScoreService {
    @Autowired
    private ScoreDao scores;

    @Autowired
    private FriendService friendService;

    public List<Score> getFilteredHighScores(int levelId, String userFilter, String viewFilter, String username) {
        if (userFilter != null && viewFilter != null) {
            String datetime = generateViewFilter(viewFilter);
            List<String> userList = generateUserFilter(userFilter, username);
            return scores.getHighScoresByLevelAfterDatetimeAndUserlist(levelId, datetime, userList);
        }
        if (userFilter != null) {
            return scores.getHighScoresByLevelUserlist(levelId, generateUserFilter(userFilter, username));
        }
        if (viewFilter != null) {
            return scores.getHighScoresByLevelAfterDatetime(levelId, generateViewFilter(viewFilter));
        }
        return scores.getHighScoresByLevel(levelId);
    }

    public List<Score> getFilteredHighScores(int levelId, String userFilter, String viewFilter, String username, int limit) {
        return null;
    }

    private static Timestamp generateDayAgo() {
        return Timestamp.from(ZonedDateTime.now().minusDays(1).toInstant());
    }

    private static Timestamp generateMonthAgo() {
        return Timestamp.from(ZonedDateTime.now().minusMonths(1).toInstant());
    }

    private static Timestamp generateYearAgo() {
        return Timestamp.from(ZonedDateTime.now().minusYears(1).toInstant());
    }

    private List<String> generateUserFilter(String userFilter, String username) {
        List<String> usernames = new ArrayList<String>();
        switch (userFilter.toLowerCase()) {
            case "me":
                usernames.add(username);
                break;
            case "friends":
                usernames = friendService.generateFriendList(username).usernames;
                break;
            default:
                usernames.add(username);
        }
        return usernames;
    }

    private static String generateViewFilter(String viewFilter) {
        switch (viewFilter.toLowerCase()) {
            case "day":
                return generateDayAgo().toString();
            case "month":
                return generateMonthAgo().toString();
            case "year":
                return generateYearAgo().toString();
            default:
                return Timestamp.from(Instant.now()).toString();
        }
    }
}