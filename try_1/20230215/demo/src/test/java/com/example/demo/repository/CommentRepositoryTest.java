//package com.example.demo.repository;
//
//import com.example.demo.entity.Article;
//import com.example.demo.entity.Comment;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//
//import java.util.Arrays;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//
//@DataJpaTest
//class CommentRepositoryTest {
//    @Autowired
//    CommentRepository commentRepository;
//
//    @Test
//    @DisplayName("특정 게시글의 모든 댓글 조회")
//    void findByAtritcleId() {
//        {
//            // 입력데이터 준비
//            Long articleId = 2L;
//
//            // 실제 수행과정
//            List<Comment> comments = commentRepository.findByAtritcleId(articleId);
//            System.out.println("[1] comments -> " + comments.toString() );
//
//
//            // 예상
//            Article article = new Article(2L, "두번째 글", "data.sql 파일에 의해 자동생성되었습니다. [2]");
//            Comment a = new Comment(2L, article, "강아지", "멍멍");
//            Comment b = new Comment(3L, article, "고양이", "냐옹");
//            List<Comment> expected = Arrays.asList( a, b );
//            System.out.println("[2] expected -> " + comments.toString() );
//
//            // 검증
//            assertEquals(expected.toString(), comments.toString());
//
//        }
//    }
//
//    @Test
//    void findByNickname() {
//    }
//}