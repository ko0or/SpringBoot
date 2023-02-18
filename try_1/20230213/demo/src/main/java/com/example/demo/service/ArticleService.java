package com.example.demo.service;

import com.example.demo.dto.ArticleForm;
import com.example.demo.entity.Article;
import com.example.demo.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    public List<Article> showAll() {
        return articleRepository.findAll();
    }

    public Article showOne(Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    public Article create(ArticleForm inputJSON) {
        Article article = inputJSON.toEntity();
        if (article.getId() != null) return null;
        return articleRepository.save(article);
    }

    public Article update(Long id, ArticleForm inputJSON) {
        // json -> dto -> entity -> return CrudRepository.save(@entity)
        // 1: 수정용 엔티티 생성
        Article article = inputJSON.toEntity();
        article.setId(id); // json에서 key(id)를 안써도되게끔 !


        // 2 : 대상 엔티티 조회
        Article target = articleRepository.findById(id).orElse(null);

        // 3 : 업데이트및 정상응답(STATUS-> 200)
        if (target != null && article.getId() == id) {
            target.NullCheck(article);
            Article updated = articleRepository.save(target);
            return updated;
        }

        // 4 : if 조건문이 실행안되었을때 (STATUS-> 400)
        return null;

    }

    public Article del(Long id) {
        Article target = articleRepository.findById(id).orElse(null);
        if (target != null) {
            articleRepository.deleteById(id);
            return target;
        }

        return null;
    }

    @Transactional // 오류발생시 해당 메소드 실행이 취소되는 어노테이션 !
    public List<Article> createList(List<ArticleForm> inputJsonList) {
        // json 요청들을 entity 형태로 하나씩 하나씩 변환해주기
        List<Article> articleList = inputJsonList.stream()
                .map(dto -> dto.toEntity())
                .collect(Collectors.toList());

        // 변환된 entity를 감싸주기
        articleList.stream()
                .forEach(article -> articleRepository.save(article));

        // 결과값 돌려주기
        return articleList;
    }
}



/*

    # public Article createList(List<ArticleForm> inputJsonList) {
    # ------------------------------------------------------------>
    [1] --> [리팩토링된 코드]
    List<Article> articleList = inputJsonList.stream()
            .map(dto -> dto.toEntity())
            .collect(Collectors.toList());

    [1] --> [원래코드]
    List<Article> articleList = new ArrayList<>();
    for (int i=0; i < inputJsonList.size(); i++) {
        ArticleForm dto = inputJsonList.get(i);
        Article entity = dto.toEntity();
        articleList.add(entity);
    }


    [2] --> [리팩토링된 코드]
    articleList.stream()
        .forEach(article -> articleRepository.save(article));

    [2] --> [원래코드]
    for (int i=0; i < inputJsonList.size(); i++) {
    ArticleForm dto = inputJsonList.get(i);
    Article entity = dto.toEntity();
    articleList.add(entity);
    }

 */