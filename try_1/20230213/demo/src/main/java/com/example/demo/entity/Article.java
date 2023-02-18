package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;



@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String content;

    public void NullCheck(Article GetArticle) {
        // JSON에서 작성된게 있으면 null ,
        // 작성된게 없으면 기존 작성된 내용을 기본값으로
        if (GetArticle.title != null) this.title = GetArticle.title;
        if (GetArticle.content != null) this.content = GetArticle.content;
    }


}
