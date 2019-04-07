package com.brajkowski.leaderboard.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class RelationshipIdentity implements Serializable {
    private static final long serialVersionUID = 1L;
    public String user_low;
    public String user_high;

    public static RelationshipIdentity normalize(RelationshipIdentity relationshipIdentity) {
        if (relationshipIdentity.user_high.compareTo(relationshipIdentity.user_low) < 0) {
            String temp = relationshipIdentity.user_high;
            relationshipIdentity.user_high = relationshipIdentity.user_low;
            relationshipIdentity.user_low = temp;
        }
        return relationshipIdentity;
    }
}