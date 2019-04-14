package com.brajkowski.leaderboard.rest;

import com.brajkowski.leaderboard.service.FriendService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/friend")
public class FriendApi {
    @Autowired
    private FriendService friendService;

    @GetMapping
    public ResponseEntity<Object> getFriends(Authentication authentication) {
        String username = authentication.getName();
        return ResponseEntity.ok(friendService.generateFriendList(username));
    }
}