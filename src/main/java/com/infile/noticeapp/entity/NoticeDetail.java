/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.infile.noticeapp.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Clatd
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "NOTICE_DETAIL")
public class NoticeDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Integer id;
    
    @Column(name="content_title", length=250)
    private String contentTitle;
    
    @Column(name="content_description", length=250)
    private String contentDescription;
        
    @Column(name="content_image_url", length=250)
    private String contentImageUrl;
    
    @Column(name="content_order")
    private Integer contentOrder;
    
    @Column(name="primary_content")
    private boolean primaryContent;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="notice_id")
    private Notice notice;
}
