package com.brajkowski.leaderboard.repository;

import com.brajkowski.leaderboard.domain.Relationship;
import com.brajkowski.leaderboard.domain.RelationshipIdentity;

import org.springframework.data.repository.CrudRepository;

public interface RelationshipRepository extends CrudRepository<Relationship, RelationshipIdentity> {
    
}