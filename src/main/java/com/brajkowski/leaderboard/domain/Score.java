package com.brajkowski.leaderboard.domain;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "scores")
public class Score {
    @Id
    public int id;
    public String username;
    public int level_id;
    public int value;
    public Timestamp timestamp;
}