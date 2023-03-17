package com.cos.blog.question;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class QuestionService {
	@Autowired private QuestionRepository questionRepository;
	
	// Question 여러개, 리스트로 돌려준다
	public List<Question> getList() {
		return questionRepository.findAll();
	}
	
	// Question 단일 항목을 돌려준다 ( PK값으로 검색함 )
	public Question findById(int pk) {
		return questionRepository.findById(pk).orElseThrow();
	}

	public void createQuestion(String subject, String content) {
		Question q = new Question().builder()
															.subject(subject)
															.content(content)
															.createDate(LocalDateTime.now())
															.build();
		questionRepository.save(q);
	}
	
}
