package com.renegades.core.controllers;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;

import com.renegades.core.model.BaseEntity;
import com.renegades.core.repository.BaseRepository;
import com.renegades.core.services.BaseService;

public abstract class BaseController<Service extends BaseService<Repository, Entity>, Repository extends BaseRepository<Entity>, Entity extends BaseEntity> {

    protected ObjectProvider<Service> serviceProvider;

    protected BaseController(ObjectProvider<Service> serviceProvider) {
        this.serviceProvider = serviceProvider;
    }

    protected Service getService() {
        return serviceProvider.getObject();
    }

    protected List<Entity> findAll() {
        return getService().findAll();
    }

    protected Entity findById(@PathVariable String id) {
        if (!StringUtils.hasLength(id)) {
            return null;
        }
        ObjectId objectId = new ObjectId(id);
        return getService().findById(objectId);
    }
}