package com.brajkowski.leaderboard.service;

import java.util.List;
import java.util.Optional;

import com.brajkowski.leaderboard.dao.DaoResult;
import com.brajkowski.leaderboard.dao.RelationshipDao;
import com.brajkowski.leaderboard.domain.Relationship;
import com.brajkowski.leaderboard.domain.RelationshipIdentity;
import com.brajkowski.leaderboard.domain.RelationshipStatus;
import com.brajkowski.leaderboard.domain.UsernameList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FriendService {
    
    @Autowired
    private RelationshipDao relationships;

    public UsernameList generateFriendList(String username) {
        UsernameList friendList = new UsernameList();
        List<Relationship> friends = relationships.getRelationshipsByUsernameAndStatus(username, RelationshipStatus.FRIEND);
        for (Relationship r : friends) {
            if (r.relationshipIdentity.user_high.compareTo(username) == 0) {
                friendList.add(r.relationshipIdentity.user_low);
                continue;
            }
            friendList.add(r.relationshipIdentity.user_high);
        }
        return friendList;
    }

    public DaoResult acceptFriendRequest(String fromUsername, String toUsername) {
        RelationshipIdentity relationshipId = new RelationshipIdentity(fromUsername, toUsername);
        Optional<Relationship> relationship = relationships.getRelationshipById(relationshipId);
        if (relationship.isPresent() && relationship.get().status == RelationshipStatus.PENDING.getValue()) {
            Relationship r = relationship.get();
            if (r.action_user.compareTo(toUsername) == 0) {
                r.status = RelationshipStatus.FRIEND.getValue();
                return relationships.updateRelationship(r);
            }
            return new DaoResult(false, Optional.of("User cannot accept their own request"));    
        }
        return new DaoResult(false, Optional.of("Request does not exist"));
    }
}