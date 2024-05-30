package com.renegades.core.controllers;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.renegades.core.model.UserEntity;
import com.renegades.core.repository.UserRepository;
import com.renegades.core.services.UserService;

@RestController
@RequestMapping(path = "/users")
public class UserController extends BaseController<UserService, UserRepository, UserEntity> {

    public UserController(ObjectProvider<UserService> userServiceProvider) {
        super(userServiceProvider);
    }
}