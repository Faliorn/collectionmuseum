package com.renegades.core.services;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.ObjectProvider;

import com.renegades.core.model.BaseEntity;
import com.renegades.core.repository.BaseRepository;

public abstract class BaseService<Repository extends BaseRepository<Entity>, Entity extends BaseEntity> {
    protected final ObjectProvider<Repository> repositoryProvider;

    protected BaseService(final ObjectProvider<Repository> repositoryProvider) {
        this.repositoryProvider = repositoryProvider;
    }

    protected Repository getRepository() {
        return repositoryProvider.getObject();
    }

    public Entity findById(ObjectId id) {
        Optional<Entity> entity = getRepository().findById(id);
        if (entity.isPresent()) {
            return entity.get();
        }
        return null;
    }

    public List<Entity> findAll() {
        return getRepository().findAll();
    }
}