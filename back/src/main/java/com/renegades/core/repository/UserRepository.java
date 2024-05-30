package com.renegades.core.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.renegades.core.model.UserEntity;

@Repository
public interface UserRepository extends BaseRepository<UserEntity> {

    Optional<UserEntity> findByEmail(String email);
}