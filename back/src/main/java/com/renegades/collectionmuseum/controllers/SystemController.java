package com.renegades.collectionmuseum.controllers;

import java.util.List;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.renegades.collectionmuseum.model.SystemEntity;
import com.renegades.collectionmuseum.repository.SystemRepository;
import com.renegades.collectionmuseum.services.SystemService;
import com.renegades.core.controllers.BaseController;

@RestController
@RequestMapping(path = "/systems")
public class SystemController extends BaseController<SystemService, SystemRepository, SystemEntity> {

    public SystemController(ObjectProvider<SystemService> systemService) {
        super(systemService);
    }

    @GetMapping
    public List<SystemEntity> findAll() {
        return findAll();
    }

    @GetMapping("/{id}")
    public SystemEntity findById(@PathVariable Long id) {
        return findById(id);
    }
}