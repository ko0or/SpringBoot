package com.example.demo.api;


import com.example.demo.dto.CommentDto;
import com.example.demo.entity.Comment;
import com.example.demo.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentApiController {
    @Autowired
    private CommentService commentService;


    // 댓글 목록조회
    @GetMapping("/api/comments/get/{articleId}")
    public ResponseEntity<List<CommentDto>> comments(@PathVariable Long articleId) {
        List<CommentDto> dtos = commentService.comments(articleId);

        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }


    // 댓글 생성
    @PostMapping("/api/comments/add/{articleId}")
    public ResponseEntity<CommentDto> add(@PathVariable Long articleId,
    @RequestBody CommentDto dto) {
        CommentDto addDto = commentService.add(articleId, dto);
        return ResponseEntity.status(HttpStatus.OK).body(addDto);
    }


    // 댓글 수정


    // 댓글 삭제



}
