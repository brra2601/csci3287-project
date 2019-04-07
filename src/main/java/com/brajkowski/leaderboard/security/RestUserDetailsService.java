package com.brajkowski.leaderboard.security;

import java.util.Optional;

import com.brajkowski.leaderboard.dao.UserDao;
import com.brajkowski.leaderboard.domain.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class RestUserDetailsService implements UserDetailsService {
    @Autowired
    private UserDao users;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = users.getUserByUsername(username);
        if (user.isPresent()) {
            return new UserDetailsImpl(user.get());
        }
        throw new UsernameNotFoundException(username);
    }
}