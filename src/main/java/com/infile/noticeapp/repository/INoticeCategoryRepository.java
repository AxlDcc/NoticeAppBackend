/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.infile.noticeapp.repository;

import com.infile.noticeapp.entity.NoticeCategory;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Clatd
 */
public interface INoticeCategoryRepository extends CrudRepository<NoticeCategory,Integer>{
    
}
