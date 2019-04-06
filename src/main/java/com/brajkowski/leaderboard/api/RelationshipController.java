package com.brajkowski.leaderboard.api;

import com.brajkowski.leaderboard.dao.RelationshipRepository;
import com.brajkowski.leaderboard.domain.Relationship;
import com.brajkowski.leaderboard.domain.RelationshipIdentity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/relationship")
public class RelationshipController {
    @Autowired
    private RelationshipRepository relationshipRepository;

    @GetMapping
    public ResponseEntity<Iterable<Relationship>> getAll() {
        return ResponseEntity.ok(relationshipRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<Relationship> create(@RequestBody RelationshipIdentity relationshipIdentity) {
        Relationship relationship = new Relationship();
        relationship.relationshipIdentity = relationshipIdentity;
        relationship.action_user = relationshipIdentity.user_low;
        relationship.status = 0;
        relationshipRepository.save(relationship);
        return ResponseEntity.ok(relationship);
    }
}