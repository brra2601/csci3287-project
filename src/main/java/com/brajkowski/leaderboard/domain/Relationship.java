package com.brajkowski.leaderboard.domain;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name = "relationships")
public class Relationship {

    @EmbeddedId
    public RelationshipIdentity relationshipIdentity;
    public String action_user;
    public int status;

    public Relationship() {

    }

    public Relationship(String fromUsername, String toUsername) {
        RelationshipIdentity relationshipIdentity = new RelationshipIdentity(fromUsername, toUsername);
        this.relationshipIdentity = relationshipIdentity;
        this.action_user = toUsername;
        this.status = RelationshipStatus.PENDING.getValue();
    }

    @JsonIgnore
    public String getNonActionUsername() {
        String user_low = relationshipIdentity.user_low;
        String user_high = relationshipIdentity.user_high;
        if (user_low.compareTo(action_user) == 0) {
            return user_high;
        }
        return user_low;
    }
}