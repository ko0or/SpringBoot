package com.example.st.dto;

import com.example.st.entity.BoardEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardDto {
    private Long id;
    private String title;
    private String content;
    private String createAt;

    public BoardEntity toEntity() {
        return new BoardEntity(id, title, content, createAt);
    }
}
