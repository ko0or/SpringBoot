package com.cos.blog.answer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cos.blog.question.Question;
import com.cos.blog.question.QuestionService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/answer")
public class AnswerController {
	
	@Autowired private QuestionService questionService;
	@Autowired AnswerService answerService;
	

	@PostMapping("/create/{id}")
	public String createAnswer(@PathVariable int id, Model model,
														 @Valid AnswerForm answerForm, BindingResult bindingResult
														 ) 
	{
		
		// 일단, 부모 DB 찾기
		Question question = questionService.findById(id);
		
		// 부모DB에 등록할 내용 검증 : 빈 공간일경우 
		if ( bindingResult.hasErrors() ) {
			model.addAttribute("question", question);
			return "question_detail";
		}
		
		// 부모DB에 등록할 내용 검증 : 정상일경우		
		answerService.createAnswer(question, answerForm.getContent());
		return String.format("redirect:/question/detail/%s", id);
		
	}
//	@PostMapping("/create/{id}")
//	public String createAnswer(@PathVariable int id,
//														 @RequestParam String content,
//														 Model model		) 
//	{
//		
//		Question question = questionService.findById(id);
//		answerService.createAnswer(question, content);
//		
//		return String.format("redirect:/question/detail/%s", id);
//		
//	}
	
	
	
}
