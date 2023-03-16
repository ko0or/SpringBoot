package com.cos.blog.question;

import java.time.LocalDateTime;
import java.util.List;

import com.cos.blog.answer.Answer;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;



@Entity
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Question {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(columnDefinition = "LONGTEXT")
	private String subject;
	
	@Column(columnDefinition = "LONGTEXT")
	private String content;
	
	private LocalDateTime createDate;

	//org.hibernate.LazyInitializationException: failed to lazily initialize a collection of role: com.cos.blog.question.Question.answerList: could not initialize proxy - no Session
	//위 오류는 ` fetch = FetchType.EAGER ` 를 사용해서 해결했다.
	@OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
	private List<Answer> answerList;
	

	
}
