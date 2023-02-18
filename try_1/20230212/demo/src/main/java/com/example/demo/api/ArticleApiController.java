package com.example.demo.api;

import com.example.demo.dto.ArticleForm;
import com.example.demo.entity.Article;
import com.example.demo.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // RestAPI용 컨트롤러,  데이터(JSON)을 반환한다 !
public class ArticleApiController {

    @Autowired
    private ArticleRepository articleRepository;

    // GET (읽어오기) -->
    // 모든정보를 가져오는 findAll을 사용, 자료형 List 사용
    @GetMapping("/api")
    public List<Article> index() {
        return articleRepository.findAll();
    }

    // {id} 정보에 해당하는 자료만 갖고오는 findById 사용
    @GetMapping("/api/get/{id}")
    public Article index(@PathVariable Long id) {
        return articleRepository.findById(id).orElse(null);
    }


    // POST (추가하기) -->
    // JSON을 받을땐, <Form>을 받을때와는 방법이 다르다
    // @RequestBody 어노테이션을 사용해야한다.
    @PostMapping("/api/create")
    public Article create(@RequestBody ArticleForm inputJSON) {
        // json -> dto -> entity -> return CrudRepository.save(@entity)
        Article article = inputJSON.toEntity();
        return articleRepository.save(article);
    }


    // PATCH (수정하기) -->
    @PatchMapping("/api/patch/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id,
                                         @RequestBody ArticleForm inputJSON) {
        // json -> dto -> entity -> return CrudRepository.save(@entity)
        // 1: 수정용 엔티티 생성
        Article article = inputJSON.toEntity();
        article.setId(id); // json에서 key(id)를 안써도되게끔 !

        // 2 : 대상 엔티티 조회
        Article target = articleRepository.findById(id).orElse(null);

        // 3 : ID
        if (target != null && article.getId() == id) {
            target.NullCheck(article);
            Article updated = articleRepository.save(target);
            return ResponseEntity.status(HttpStatus.OK).body(updated);
        }

        // 4 : 업데이트및 정상응답(STATUS-> 200)
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }



    // DELETE -->
    @DeleteMapping("/api/del/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        Article target = articleRepository.findById(id).orElse(null);

        if (target != null) {
            articleRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("삭제성공");
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("삭제실패");
    }

}
