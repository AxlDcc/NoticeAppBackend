package com.infile.noticeapp.service;

import com.infile.noticeapp.entity.User;
import com.infile.noticeapp.repository.IUserRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Clatd
 */
@Service
@RequiredArgsConstructor

public class AuthUserDetailService implements UserDetailsService {
    private final IUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> users = userRepository.findUserByEmailAndState(username, true);
        if (users.isEmpty()) return null;
        List<String> roles = new ArrayList<>();
        roles.add("USER");
        return org.springframework.security.core.userdetails.User.builder().username(users.get().getEmail()).password(users.get().getPassword()).roles(roles.toArray(String[]::new)).build();
    }
    
    public String userId(String username) {
        Optional<User> user = userRepository.findUserByEmailAndState(username,true);
        return user.map(value -> value.getId().toString()).orElse(null);
    }
}
