package com.example.fgocalculation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.fgocalculation.entity.UserEntity;
import com.example.fgocalculation.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        if (email == null || "".equals(email)) {
            throw new UsernameNotFoundException("e-mail is empty");
        }
        UserEntity entity = repository.findByEmail(email);

        return entity;
    }

}
