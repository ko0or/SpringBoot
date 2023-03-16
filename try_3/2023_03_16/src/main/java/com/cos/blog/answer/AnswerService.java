package com.cos.blog.answer;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cos.blog.question.Question;



@Service
public class AnswerService {
	@Autowired AnswerRepository answerRepository;
	
	public List<Answer> getList() { 
		return answerRepository.findAll(); 
		}
	
	public Answer findById(int pk) {
		return answerRepository.findById(pk).orElseThrow();
	}
	
	public void createAnswer(Question question, String content) {
		Answer answer = new Answer().builder()	
																.content(content)
																.createDate(LocalDateTime.now())
																.question(question)
																.build();
		answerRepository.save(answer);
		
	}
	
}
