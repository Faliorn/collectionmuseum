package com.renegades.collectionmuseum.services;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Service;

import com.renegades.collectionmuseum.model.SystemEntity;
import com.renegades.collectionmuseum.repository.SystemRepository;
import com.renegades.core.services.BaseService;

@Service("systemService")
public class SystemService extends BaseService<SystemRepository, SystemEntity> {

    public SystemService(ObjectProvider<SystemRepository> systemRepositoryProvider) {
        super(systemRepositoryProvider);
    }
}