package com.brajkowski.leaderboard.service;

import java.util.ArrayList;
import java.util.List;

import com.brajkowski.leaderboard.dao.RelationshipDao;
import com.brajkowski.leaderboard.domain.Relationship;
import com.brajkowski.leaderboard.domain.RelationshipStatus;
import com.brajkowski.leaderboard.domain.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FriendService {
    
    @Autowired
    private RelationshipDao relationships;

    public List<String> generateFriendList(String username) {
        ArrayList<String> friendList = new ArrayList<String>();
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