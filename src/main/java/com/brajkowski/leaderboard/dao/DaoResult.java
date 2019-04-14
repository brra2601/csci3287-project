package com.brajkowski.leaderboard.dao;

import java.util.Optional;

public class DaoResult {
    private boolean success;
    private Optional<String> message;

    public DaoResult(boolean didSucceed, Optional<String> message) {
        this.success = didSucceed;
        this.message = message;
    }

    public Optional<String> getMessage() { return this.message; }
    public boolean didSucceed() { return this.success; }
}