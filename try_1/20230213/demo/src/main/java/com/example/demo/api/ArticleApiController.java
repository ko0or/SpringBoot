package com.example.demo.api;

import com.example.demo.dto.ArticleForm;
import com.example.demo.entity.Article;
import com.example.demo.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController // RestAPI용 컨트롤러,  데이터(JSON)을 반환한다 !
public class ArticleApiController {

    @Autowired
    private ArticleService articleService;

    // GET (읽어오기) -->
    // 모든정보를 가져오는 findAll을 사용, 자료형 List 사용
    @GetMapping("/api")
    public List<Article> index() {
        return articleService.showAll();
    }

    // {id} 정보에 해당하는 자료만 갖고오는 findById 사용
    @GetMapping("/api/get/{id}")
    public Article selected(@PathVariable Long id) {
        return articleService.showOne(id);
    }


    // POST (추가하기) -->
    // JSON을 받을땐, <Form>을 받을때와는 방법이 다르다
    // @RequestBody 어노테이션을 사용해야한다.
    @PostMapping("/api/create")
    public ResponseEntity<Object> create(@RequestBody ArticleForm inputJSON) {
        // json -> dto -> entity -> return CrudRepository.save(@entity)
        Article created = articleService.create(inputJSON);
        return (created != null) ? ResponseEntity.status(HttpStatus.OK).body(created) : ResponseEntity.status(HttpStatus.BAD_REQUEST).body("실패");
    }


    // PATCH (수정하기) -->
    @PatchMapping("/api/patch/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id,
                                         @RequestBody ArticleForm inputJSON) {

        Article updated = articleService.update(id, inputJSON);
        return (updated!=null) ?
                ResponseEntity.status(HttpStatus.OK).body(updated):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }



    // DELETE -->
    @DeleteMapping("/api/del/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        Article article = articleService.del(id);
        return (article != null) ?
                ResponseEntity.status(HttpStatus.OK).body(article):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body("삭제하려는 대상이 없어요 !");
    }


    @PostMapping("/api/taction/test")
    public ResponseEntity<List<Article>> transactionTest(@RequestBody List<ArticleForm> inputJsonList) {
        List<Article> createdList = articleService.createList(inputJsonList);
        return  (createdList != null) ?
                ResponseEntity.status(HttpStatus.OK).body(createdList):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
