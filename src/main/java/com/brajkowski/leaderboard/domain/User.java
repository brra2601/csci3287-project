package com.brajkowski.leaderboard.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "users")
public class User {
    @Id
    public String username;
    public String password;
    public String email;
    public String name;
}