package com.brajkowski.leaderboard.rest;

import java.util.List;

import javax.xml.ws.Response;

import com.brajkowski.leaderboard.dao.RelationshipDao;
import com.brajkowski.leaderboard.domain.DaoCreationResult;
import com.brajkowski.leaderboard.domain.Relationship;
import com.brajkowski.leaderboard.domain.RelationshipIdentity;
import com.brajkowski.leaderboard.service.FriendService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/relationship")
public class RelationshipApi {
    @Autowired
    private RelationshipDao relationships;

    @Autowired
    private FriendService friendService;

    @GetMapping
    public ResponseEntity<Iterable<Relationship>> getAll() {
        return ResponseEntity.ok(relationships.getRelationships());
    }

    @GetMapping(value = "/{username}")
    public ResponseEntity<Iterable<Relationship>> getByUsername(@PathVariable String username) {
        return ResponseEntity.ok(relationships.getRelationshipsByUsername(username));
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody RelationshipIdentity relationshipIdentity) {
        Relationship relationship = new Relationship();
        relationship.relationshipIdentity = RelationshipIdentity.normalize(relationshipIdentity);
        relationship.action_user = relationshipIdentity.user_low;
        relationship.status = 0;
        DaoCreationResult result = relationships.addRelationship(relationship);
        if (result.didSucceed()) {
            return ResponseEntity.ok(relationship);
        }
        return ResponseEntity.badRequest().body(result.getMessage());
    }

    @GetMapping(path = "/friendlist")
    public ResponseEntity<Object> getFriends(@RequestParam(value = "username") String username) {
        return ResponseEntity.ok(friendService.generateFriendList(username));
        
    }
}