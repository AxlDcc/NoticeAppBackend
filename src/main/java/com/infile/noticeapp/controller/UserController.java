/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.infile.noticeapp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.infile.noticeapp.model.UserCreate;
import com.infile.noticeapp.model.UserLoginDto;
import com.infile.noticeapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Clatd
 */
@RestController
@RequestMapping(value = "v1/auth", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class UserController {
    private final UserService service;

    @PostMapping("/login")
    ResponseEntity<?> login(@RequestBody UserLoginDto param) throws JsonProcessingException {
        return service.signin(param);
    }

    @PostMapping("/signup")
    ResponseEntity<?> signup(@RequestBody UserCreate param) throws JsonProcessingException {
        return service.signup(param);
    }
}
