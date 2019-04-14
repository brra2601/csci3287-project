package com.brajkowski.leaderboard.rest;

import com.brajkowski.leaderboard.service.RequestService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/request")
public class RequestApi {
    @Autowired
    private RequestService requests;

    @GetMapping
    public ResponseEntity<Object> getFriendRequests(Authentication authentication) {
        String username = authentication.getName();
        return ResponseEntity.ok(requests.generateFriendRequestList(username));
    }

    @PostMapping
    public ResponseEntity<Object> createFriendRequest(@RequestParam(value = "username") String toUsername, Authentication authentication) {
        String fromUsername = authentication.getName();
        return ResponseEntity.ok(requests.createFriendRequest(fromUsername, toUsername) );
    }
}