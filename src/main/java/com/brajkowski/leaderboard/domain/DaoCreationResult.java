package com.brajkowski.leaderboard.domain;

import java.util.Optional;

public class DaoCreationResult {
    private boolean success;
    private Optional<String> message;

    public DaoCreationResult(boolean didSucceed, Optional<String> message) {
        this.success = didSucceed;
        this.message = message;
    }

    public Optional<String> getMessage() { return this.message; }
    public boolean didSucceed() { return this.success; }
}