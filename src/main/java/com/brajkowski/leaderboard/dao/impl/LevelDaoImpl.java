package com.brajkowski.leaderboard.dao.impl;

import java.util.List;
import java.util.Optional;

import com.brajkowski.leaderboard.dao.LevelDao;
import com.brajkowski.leaderboard.domain.Level;
import com.brajkowski.leaderboard.repository.LevelRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LevelDaoImpl implements LevelDao {

    @Autowired
    private LevelRepository levelRepository;

    @Override
    public List<Level> getAllLevels() {
        return levelRepository.findAll();
    }

    @Override
    public Optional<Level> getLevelById(int id) {
        return levelRepository.findById(id);
    }

    @Override
    public Boolean existsById(int id) {
        return levelRepository.existsById(id);
    }

}