package com.brajkowski.leaderboard.domain;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity(name = "relationships")
public class Relationship {
    // public enum RelationshipTypeEnum {
    //     PENDING(0), FRIENDS(1);
    //     private final int value;
    //     RelationshipTypeEnum(int value) { this.value = value; }
    //     public int getValue() { return this.value; }
    // }

    @EmbeddedId
    public RelationshipIdentity relationshipIdentity;
    public String action_user;
    public int status;
}