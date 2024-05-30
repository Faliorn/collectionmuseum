package com.renegades.core.services;

import java.util.Optional;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.renegades.core.model.UserEntity;
import com.renegades.core.repository.UserRepository;

@Service
public class UserService extends BaseService<UserRepository, UserEntity> implements UserDetailsService {
    private final PasswordEncoder encoder;

    public UserService(ObjectProvider<UserRepository> userRepositoryProvider, PasswordEncoder encoder) {
        super(userRepositoryProvider);
        this.encoder = encoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> userEntity = getRepository().findByUsername(username);
        // Converting user entity to UserDetails
        UserEntity u = userEntity.orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return User.builder().username(u.getEmail()).password(u.getPassword()).build();
    }

    public UserEntity create(UserEntity userEntity) {
        userEntity.setPassword(encoder.encode(userEntity.getPassword()));
        return getRepository().save(userEntity);
    }
}