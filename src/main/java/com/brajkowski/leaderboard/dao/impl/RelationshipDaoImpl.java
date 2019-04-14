package com.brajkowski.leaderboard.dao.impl;

import java.util.List;
import java.util.Optional;

import com.brajkowski.leaderboard.dao.RelationshipDao;
import com.brajkowski.leaderboard.dao.DaoResult;
import com.brajkowski.leaderboard.domain.Relationship;
import com.brajkowski.leaderboard.domain.RelationshipIdentity;
import com.brajkowski.leaderboard.domain.RelationshipStatus;
import com.brajkowski.leaderboard.repository.RelationshipRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RelationshipDaoImpl implements RelationshipDao {

    @Autowired
    private RelationshipRepository relationshipRepository;

    @Override
    public List<Relationship> getRelationships() {
        return relationshipRepository.findAll();
    }

    @Override
    public Optional<Relationship> getRelationshipById(RelationshipIdentity id) {
        return relationshipRepository.findById(id);
    }

    // @Override
    // public Optional<Relationship> getRelationshipByIdAndStatus(RelationshipIdentity id, RelationshipStatus status) {
    //     return relationshipRepository.findByIdAndStatus(id, status.getValue());
    // }

    @Override
    public List<Relationship> getRelationshipsByUsername(String username) {
        return relationshipRepository.findByUsername(username);
    }

    @Override
    public List<Relationship> getRelationshipsByUsernameAndStatus(String username, RelationshipStatus relationshipStatus) {
        return relationshipRepository.findByUsernameAndStatus(username, relationshipStatus.getValue());
    }

    @Override
    public DaoResult addRelationship(Relationship relationship) {
        if (relationshipRepository.existsById(relationship.relationshipIdentity)) {
            String message = "Relationship already exists";
            return new DaoResult(false, Optional.of(message));
        }
        relationshipRepository.save(relationship);
        return new DaoResult(true, null);
    }

    @Override
    public DaoResult updateRelationship(Relationship relationship) {
        if (relationshipRepository.existsById(relationship.relationshipIdentity)) {
            relationshipRepository.save(relationship);
            return new DaoResult(true, Optional.of("Relationship updated"));
        }
        return new DaoResult(false, Optional.of("Relationship does not exist"));
    }
}