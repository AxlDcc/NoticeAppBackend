/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.infile.noticeapp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.infile.noticeapp.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Clatd
 */
@RestController
@RequestMapping(value = "v1/notice", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class NoticeController {
    
    private final NoticeService service;
    
    
    @GetMapping("/")
    public ResponseEntity<?> getAllNotices() throws JsonProcessingException {
        return service.getNotices();
    }
    
    @GetMapping("category")
    public ResponseEntity<?> getAllCategories() throws JsonProcessingException {
        return service.getCategories();
    }
    
    @GetMapping("category/{id}")
    public ResponseEntity<?> getNoticeByCategoryId(@PathVariable Integer id) throws JsonProcessingException {
        return service.getNoticeByCategoryId(id);
    }
    
    @GetMapping("detail/{id}")
    public ResponseEntity<?> getNoticeDetailByNoticeId(@PathVariable Integer id) throws JsonProcessingException {
        return service.getNoticeDetailByNoticeId(id);
    }
    
    @GetMapping("suggestion/{id}")
    public ResponseEntity<?> getNoticesSuggestionsByNoticeId(@PathVariable Integer id) throws JsonProcessingException {
        return service.getNoticesSuggestionsByNoticeId(id);
    }
}
