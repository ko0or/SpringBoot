package com.example.demo.service;

import com.example.demo.dto.CommentDto;
import com.example.demo.entity.Article;
import com.example.demo.entity.Comment;
import com.example.demo.repository.ArticleRepository;
import com.example.demo.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ArticleRepository articleRepository;


    public List<CommentDto> comments(Long articleId) {
        // 조회: 댓글 목록
//        List<Comment> comments = commentRepository.findByAtritcleId(articleId);

        // 변환: entity -> dto
//        List<CommentDto> dtos = new ArrayList<CommentDto>();
//        for (int i=0; i < comments.size(); i++) {
//            Comment c = comments.get(i);
//            CommentDto dto = CommentDto.createCommentDto( c );
//            dtos.add( dto );
//        }

        // 반환
        return commentRepository.findByArtitcleId(articleId)
                .stream()
                .map(comment -> CommentDto.createtDto(comment))
                .collect(Collectors.toList());
    }


    @Transactional
    public CommentDto add(Long articleId, CommentDto dto) {
        // 게시글 조회및 없으면 아래내용 진행없이 중단처리
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지않는 게시물번호") );

        // 댓글 엔티티 생성  ( 게시글이 존재한다면 ) ( DB에 저장보내기위해서 )
        Comment comment = Comment.createEntity(dto, article);

        // 댓글 엔티티를 DB로 저장
        Comment Added = commentRepository.save(comment);

        // 결과값 돌려주기 (CommentApiController 에게)
        return CommentDto.createtDto(Added);
    }

    @Transactional
    public CommentDto update(Long id, CommentDto dto) {
        // 댓글 조회및 예외발생
        Comment target = commentRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("변경할 대상이 없습니다."));

        // 댓글 수정
        target.patch(dto);

        // DB로 갱신
        Comment updated = commentRepository.save(target);


        // 댓글 엔티티를 DTO로 변환 및 반환
        return CommentDto.createtDto(updated);

    }

    @Transactional
    public CommentDto delete(Long id) {
        // 댓글조회및 예외발생
        Comment cmt = commentRepository.findById(id)
                .orElseThrow( ()-> new IllegalArgumentException("오류") );

        // 댓글삭제
         commentRepository.deleteById(id);

         // 삭제
        return CommentDto.createtDto(cmt);

    }
}
