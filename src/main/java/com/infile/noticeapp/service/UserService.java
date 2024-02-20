/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.infile.noticeapp.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.infile.noticeapp.entity.User;
import com.infile.noticeapp.model.AuthTokenDto;
import com.infile.noticeapp.model.UserCreate;
import com.infile.noticeapp.model.UserLoginDto;
import com.infile.noticeapp.repository.IUserRepository;
import com.infile.noticeapp.utils.JwtUtil;
import jakarta.transaction.Transactional;
import java.util.Date;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author Clatd
 */

@Service
@RequiredArgsConstructor
public class UserService {
    private final AuthUserDetailService userDetailsService;
    private final PasswordEncoder encoder;
    private final JwtUtil jwtUtil;
    private final IUserRepository userRepository;


    public ResponseEntity<?> signin(UserLoginDto param) throws JsonProcessingException {
        UserDetails userDetails = userDetailsService.loadUserByUsername(param.getEmail());
        if (userDetails == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        
        if (!encoder.matches(param.getPassword(), userDetails.getPassword())) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        
                
        return new ResponseEntity<>(new AuthTokenDto(jwtUtil.generate(param.getEmail(), "ACCESS")),HttpStatus.OK);
    }

    @Transactional(rollbackOn = RuntimeException.class)
    public ResponseEntity<?> signup(UserCreate param) throws JsonProcessingException {
        
        Optional<User> userEntity = userRepository.findUserByEmailAndState(param.getEmail(),true);
        
        if (userEntity.isPresent()) return new ResponseEntity<>("Usuario ya existe.",HttpStatus.BAD_REQUEST);
        
        User user = new User();
        
        user.setPassword(encoder.encode(param.getPassword()));
        user.setEmail(param.getEmail());
        user.setCreatedAt(new Date());
        user.setUpdatedAt(new Date());
        user.setLastLogin(new Date());
        //Default values 
        user.setConfirmEmail(true);
        user.setState(true);
        user = userRepository.save(user);
        
        if(user.getId() > 0){
            return new ResponseEntity<>(new AuthTokenDto(jwtUtil.generate(param.getEmail(), "ACCESS")),HttpStatus.OK);
        }
        
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }    
}
