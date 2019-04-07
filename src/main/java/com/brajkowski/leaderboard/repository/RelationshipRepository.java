package com.brajkowski.leaderboard.repository;

import java.util.List;

import com.brajkowski.leaderboard.domain.Relationship;
import com.brajkowski.leaderboard.domain.RelationshipIdentity;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface RelationshipRepository extends CrudRepository<Relationship, RelationshipIdentity> {
    @Override
    List<Relationship> findAll();

    @Query(value = "SELECT * FROM relationships WHERE user_low = :username OR user_high = :username", nativeQuery=true)
    List<Relationship> findByUsername(@Param("username") String username);

    @Query(value = "SELECT * FROM relationships WHERE (relationships.user_low = :username OR relationships.user_high = :username) AND status = :status", nativeQuery = true)
    List<Relationship> findByUsernameAndStatus(@Param("username") String username, @Param("status") int status);
}