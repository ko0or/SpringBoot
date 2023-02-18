package com.example.demo.controller;
import com.example.demo.dto.ArticleForm;
import com.example.demo.entity.Article;
import com.example.demo.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@Slf4j
public class ArticleController {

    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping("/articles/new")
    public String newArticleForm() {
        return "articles/new";
    }


    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form) {
//    public String createArticle(String title, String content) {
//        System.out.println(
//                "제목 > " + title + "\n" +
//                "내용 > " + content
//        );

        // ArticleForm 클래스 (DTO)
        log.info( form.toString() );
        // System.out.println( form.toString() );

        // DTO -> Entity
        Article article = form.toEntity();
        log.info ( article.toString() );
        // System.out.println( article.toString() );

        // Entity -> Repository
        Article saved = articleRepository.save(article);
        log.info ( saved.toString() );
        // System.out.println( saved.toString() );

        return "redirect:/articles/" + saved.getId();
    }

    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model) {
        // articleRepository 메소드를 통해 정보를 모델로 저장하고, 뷰로 보여줌 !
        Article articleEntity = articleRepository.findById(id).orElse(null);
        model.addAttribute("article_model", articleEntity);
        return "articles/show";
    }

    @GetMapping("/articles")
    public String index(Model model) {
        // 정보를 갖고옴
        List<Article> articleEntityList = articleRepository.findAll();

        // 모델에 등록
        model.addAttribute("articleList", articleEntityList);

        // 뷰 설정
        return "articles/index";
    }


    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        Article article = articleRepository.findById(id).orElse(null);
        model.addAttribute("edit_model", article);
        return "articles/edit";
    }

    @PostMapping("/articles/update")
    public String update(ArticleForm form) {
        log.info ( form.toString() );

        Article articleEntity = form.toEntity();
        log.info( articleEntity.toString() );
        Article target = articleRepository.findById( articleEntity.getId() ).orElse(null);
        if (target != null) {
            // 기존 데이터가 존재한다면, 새로 저장된다.
            articleRepository.save(articleEntity);
            log.info( articleEntity.toString() );
        }
        return "redirect:/articles/" + articleEntity.getId();
    }

    @GetMapping("/articles/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rattr) {
        if (articleRepository.findById(id).orElse(null) != null) {
            // rattr은, layouts>header.mustache 가장 하단부와 연동된다.
            articleRepository.deleteById(id);
            rattr.addFlashAttribute("msg", "게시글이 삭제되었습니다 :)");
        }
        return "redirect:/articles";
    }


}
