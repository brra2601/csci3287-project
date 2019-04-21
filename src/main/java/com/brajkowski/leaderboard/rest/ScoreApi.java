package com.brajkowski.leaderboard.rest;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

import com.brajkowski.leaderboard.dao.DaoResult;
import com.brajkowski.leaderboard.dao.ScoreDao;
import com.brajkowski.leaderboard.domain.Score;
import com.brajkowski.leaderboard.service.ScoreService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/score")
public class ScoreApi {
    @Autowired
    private ScoreDao scores;

    @Autowired
    private ScoreService scoreService;

    @GetMapping
    public List<Score> getAllScores() {
        return scores.getAllScores();
    }

    @GetMapping(value = "/filter")
    public List<Score> getFilteredScores(   @RequestParam(value = "level", required = true) Integer levelId,
                                            @RequestParam(value = "user", required = false) String userFilter,
                                            @RequestParam(value = "view", required = false) String viewFilter,
                                            @RequestParam(value = "limit", required = false) Integer limit,
                                            Authentication authentication
                                        ) {
        String username = authentication.getName();
        return scoreService.getFilteredHighScores(levelId, userFilter, viewFilter, username);
    }

    @PostMapping
    public ResponseEntity<Object> createScore(@RequestParam(value = "level") int level, @RequestParam(value = "score") int scoreValue, Authentication authentication) {
        Score score = new Score();
        score.level_id = level;
        score.username = authentication.getName();
        score.value = scoreValue;
        score.timestamp = Timestamp.from(Instant.now());
        DaoResult result = scores.addScore(score);
        if (result.didSucceed()) {
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }
}