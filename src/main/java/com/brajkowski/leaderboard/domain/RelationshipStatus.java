package com.brajkowski.leaderboard.domain;

public enum RelationshipStatus {
    PENDING(0), FRIEND(1);

    private final int status;
    private RelationshipStatus(int status) { this.status = status; }
    public int getValue() { return this.status; }
}