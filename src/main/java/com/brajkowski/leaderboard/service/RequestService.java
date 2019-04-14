package com.brajkowski.leaderboard.service;

import java.util.List;

import com.brajkowski.leaderboard.dao.DaoResult;
import com.brajkowski.leaderboard.dao.RelationshipDao;
import com.brajkowski.leaderboard.domain.Relationship;
import com.brajkowski.leaderboard.domain.RelationshipStatus;
import com.brajkowski.leaderboard.domain.UsernameList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RequestService {
    @Autowired
    private RelationshipDao relationships;

    public UsernameList generateFriendRequestList(String username) {
        UsernameList friendRequestList = new UsernameList();
        List<Relationship> requests = relationships.getRelationshipsByUsernameAndStatus(username, RelationshipStatus.PENDING);
        for (Relationship request : requests) {
            if (request.action_user.compareTo(username) == 0)
                friendRequestList.add(request.getNonActionUsername());
        }
        return friendRequestList;
    }
    
    public DaoResult createFriendRequest(String fromUsername, String toUsername) {
        Relationship relationship = new Relationship(fromUsername, toUsername);
        DaoResult result = relationships.addRelationship(relationship);
        return result;
    }
}