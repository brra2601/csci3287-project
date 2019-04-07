package com.brajkowski.leaderboard.domain;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity(name = "relationships")
public class Relationship {

    @EmbeddedId
    public RelationshipIdentity relationshipIdentity;
    public String action_user;
    public int status;
}