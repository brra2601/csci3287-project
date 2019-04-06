package com.brajkowski.leaderboard.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class RelationshipIdentity implements Serializable {
    private static final long serialVersionUID = 1L;
    public String user_low;
    public String user_high;
}