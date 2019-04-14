package com.brajkowski.leaderboard.domain;

import java.util.ArrayList;

public class FriendList {
    public int count;
    public ArrayList<String> usernames;

    public FriendList() {
        this.count = 0;
        this.usernames = new ArrayList<String>();
    }

    public void add(String username) {
        this.usernames.add(username);
        this.count++;
    }
}