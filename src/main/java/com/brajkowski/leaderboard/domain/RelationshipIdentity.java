package com.brajkowski.leaderboard.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class RelationshipIdentity implements Serializable {
    private static final long serialVersionUID = 1L;
    public String user_low;
    public String user_high;

    public RelationshipIdentity() {

    }

    public RelationshipIdentity(String user1, String user2) {
        this.user_low = user1;
        this.user_high = user2;
        normalize();
    }

    private void normalize() {
        if (user_high.compareTo(user_low) < 0) {
            String temp = user_high;
            user_high = user_low;
            user_low = temp;
        }
    }
}