package com.cos.blog.answer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class AnswerService {
	@Autowired AnswerRepository answerRepository;
	
	public List<Answer> getList() { 
		return answerRepository.findAll(); 
		}
	
	public Answer findById(int pk) {
		return answerRepository.findById(pk).orElseThrow();
	}
	
}
