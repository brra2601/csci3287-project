package com.brajkowski.leaderboard.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "levels")
public class Level {
    @Id
    public int id;
    public String name;
}