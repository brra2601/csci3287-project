package com.brajkowski.leaderboard.service;

import java.util.List;

import com.brajkowski.leaderboard.dao.RelationshipDao;
import com.brajkowski.leaderboard.domain.Relationship;
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
}