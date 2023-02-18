package com.example.demo.entity;


import com.example.demo.dto.CommentDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="article_id")
    private Article article;

    @Column
    private String nickname;

    @Column
    private String body;


    public static Comment createEntity(CommentDto dto, Article article) {
        // 예외처리 1
        if (dto.getId() != null)
            throw new IllegalArgumentException("id입력이 누락되었습니다.");

        // 예외처리 2
        if (dto.getArticleId() != null)
            throw new IllegalArgumentException("id가 잘못 입력되었습니다");

        // 엔티티 생성및 반환
        return new Comment(
                dto.getId(),
                article,
                dto.getNickname(),
                dto.getBody()
        );
    }

    public void patch(CommentDto dto) {
        // 예외 발생
        if (this.id != dto.getId())
            throw  new IllegalArgumentException("주소와 게시글 번호가 다름");

        // 객체를 갱신
        if (dto.getNickname() != null)
            this.nickname = dto.getNickname();

        if (dto.getBody() != null)
            this.body = dto.getBody();
    }
}
