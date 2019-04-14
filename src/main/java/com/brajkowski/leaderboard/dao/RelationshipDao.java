package com.brajkowski.leaderboard.dao;

import java.util.List;
import java.util.Optional;

import com.brajkowski.leaderboard.dao.DaoResult;
import com.brajkowski.leaderboard.domain.Relationship;
import com.brajkowski.leaderboard.domain.RelationshipIdentity;
import com.brajkowski.leaderboard.domain.RelationshipStatus;

import org.springframework.stereotype.Repository;

@Repository
public interface RelationshipDao {
    public List<Relationship> getRelationships();
    public Optional<Relationship> getRelationshipById(RelationshipIdentity relationshipIdentity);
    // public Optional<Relationship> getRelationshipByIdAndStatus(RelationshipIdentity relationshipIdentity, RelationshipStatus status);
    public List<Relationship> getRelationshipsByUsername(String username);
    public List<Relationship> getRelationshipsByUsernameAndStatus(String username, RelationshipStatus relationshipStatus);
    public DaoResult addRelationship(Relationship relationship);
    public DaoResult updateRelationship(Relationship relationship);
}