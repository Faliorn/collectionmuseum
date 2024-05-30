package com.renegades.core.services;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;

import com.renegades.core.model.BaseEntity;
import com.renegades.core.repository.BaseRepository;

public abstract class BaseService<Repository extends BaseRepository<Entity>, Entity extends BaseEntity> {
    protected final Repository repository;

    public BaseService(final Repository repository) {
        this.repository = repository;
    }

    public Entity findById(ObjectId id) {
        Optional<Entity> entity = repository.findById(id);
        if (entity.isPresent()) {
            return entity.get();
        }
        return null;
    }

    public List<Entity> findAll() {
        return repository.findAll();
    }
}