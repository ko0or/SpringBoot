//package com.example.demo.service;
//
//import com.example.demo.entity.Article;
//import com.example.demo.repository.ArticleRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class ArticleService {
//    @Autowired
//    private ArticleRepository articleRepository;
//
//    public List<Article> showAll() {
//        return articleRepository.findAll();
//    }
//
//    public Article showOne(Long id) {
//        return articleRepository.findById(id).orElse(null);
//    }
//
//}
