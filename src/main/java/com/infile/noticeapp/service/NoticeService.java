package com.infile.noticeapp.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.infile.noticeapp.entity.Notice;
import com.infile.noticeapp.entity.NoticeCategory;
import com.infile.noticeapp.entity.NoticeDetail;
import com.infile.noticeapp.model.AuthTokenDto;
import com.infile.noticeapp.repository.INoticeCategoryRepository;
import com.infile.noticeapp.repository.INoticeDetailRepository;
import com.infile.noticeapp.repository.INoticeRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
public class NoticeService {
    private final INoticeRepository noticeRepository;
    private final INoticeCategoryRepository categoryRepository;
    private final INoticeDetailRepository detailRepository;

    
    public ResponseEntity<?> getNotices() throws JsonProcessingException{
        Iterable<Notice> notices = noticeRepository.findAll();
        if(notices == null) return new ResponseEntity<>(notices,HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(notices,HttpStatus.OK);
    }
    
    public ResponseEntity<?> getCategories() throws JsonProcessingException{
        ArrayList<NoticeCategory> categories = (ArrayList<NoticeCategory>) categoryRepository.findAll();
        if(categories == null) return new ResponseEntity<>(categories,HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(categories,HttpStatus.OK);
    }
    
    public ResponseEntity<?> getNoticeDetailByNoticeId(Integer id) throws JsonProcessingException{
        Iterable<NoticeDetail> detail = detailRepository.findAllByNoticeId(id);
        if(detail == null) return new ResponseEntity<>(detail,HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(detail,HttpStatus.OK);
    }
    
    public ResponseEntity<?> getNoticeByCategoryId(Integer id) throws JsonProcessingException{
        List<Notice> notices = noticeRepository.findAllByNoticeCategoryId(id);
        if(notices == null) return new ResponseEntity<>(notices,HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(notices,HttpStatus.OK);
    }
    
    public ResponseEntity<?> getNoticesSuggestionsByNoticeId(Integer id) throws JsonProcessingException{
        
        List<Notice> suggestions = new ArrayList();
        //Find notice
        Optional<Notice> notice = noticeRepository.findById(id);
        
        if(notice.isPresent()){
            //Find notice matchs Tags
            List<Notice> tags = noticeRepository.findAllByTagContaining(notice.get().getTag());
            //Find notice match title
            List<Notice> title = noticeRepository.findAllByTitleContaining(notice.get().getTitle());
            //Find notice match Description
            List<Notice> description = noticeRepository.findAllBySumaryContentContaining(notice.get().getSumaryContent());

        
            if(!tags.isEmpty()){
                suggestions.addAll(tags);
            }

            if(!title.isEmpty()){
                suggestions.addAll(tags);
            }

            if(!description.isEmpty()){
                suggestions.addAll(tags);
            }

            //Return object
            if(!suggestions.isEmpty()){
                suggestions = suggestions.stream().distinct().collect(Collectors.toList());
                suggestions.removeIf(n -> n.getId() == notice.get().getId());
                return new ResponseEntity<>(suggestions,HttpStatus.OK);
            }
            
            return new ResponseEntity<>(suggestions,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(suggestions,HttpStatus.NOT_FOUND);
    }
}
