package com.brajkowski.leaderboard.dao;

import java.util.List;
import java.util.Optional;

import com.brajkowski.leaderboard.dao.DaoCreationResult;
import com.brajkowski.leaderboard.domain.Relationship;
import com.brajkowski.leaderboard.domain.RelationshipIdentity;
import com.brajkowski.leaderboard.domain.RelationshipStatus;

import org.springframework.stereotype.Repository;

@Repository
public interface RelationshipDao {
    public List<Relationship> getRelationships();
    public Optional<Relationship> getRelationshipById(RelationshipIdentity relationshipIdentity);
    public List<Relationship> getRelationshipsByUsername(String username);
    public List<Relationship> getRelationshipsByUsernameAndStatus(String username, RelationshipStatus relationshipStatus);
    public DaoCreationResult addRelationship(Relationship relationship);
}