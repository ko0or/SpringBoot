package com.example.demo.dto;

import com.example.demo.entity.Comment;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommentDto {
    private Long id;
    private Long articleId;
    private String nickname;
    private String body;


    public static CommentDto createtDto(Comment comment) {
        return new CommentDto(
                comment.getId(),
                comment.getArticle().getId(),
                comment.getNickname(),
                comment.getBody()
        );
    }
}
