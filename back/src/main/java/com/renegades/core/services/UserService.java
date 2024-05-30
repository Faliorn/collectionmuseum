package com.renegades.core.services;

import java.util.Optional;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.renegades.core.model.UserEntity;
import com.renegades.core.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository repository;
    private final PasswordEncoder encoder;

    public UserService(UserRepository repository, PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<UserEntity> userEntity = repository.findByEmail(email);
        // Converting user entity to UserDetails
        UserEntity u = userEntity.orElseThrow();
        return User.builder().username(u.getEmail()).password(u.getPassword()).build();
    }

    public String addUser(UserEntity userEntity) {
        userEntity.setPassword(encoder.encode(userEntity.getPassword()));
        repository.save(userEntity);
        return "User Added Successfully";
    }
}