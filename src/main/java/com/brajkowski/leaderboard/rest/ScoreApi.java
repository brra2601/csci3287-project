package com.brajkowski.leaderboard.rest;

import java.util.List;

import com.brajkowski.leaderboard.dao.ScoreDao;
import com.brajkowski.leaderboard.domain.Score;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/score")
public class ScoreApi {
    @Autowired
    private ScoreDao scores;

    @GetMapping
    public List<Score> getAllScores() {
        return scores.getAllScores();
    }
}