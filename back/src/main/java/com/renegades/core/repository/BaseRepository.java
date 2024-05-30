package com.renegades.core.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BaseRepository<Entity> extends MongoRepository<Entity, ObjectId> {
}