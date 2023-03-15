package com.cos.blog.question;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cos.blog.answer.Answer;
import com.cos.blog.answer.AnswerRepository;
import com.cos.blog.answer.AnswerService;

@Controller
public class QuestionController {
	
	@Autowired	private QuestionRepository questionRepository;
	@Autowired	private AnswerRepository answerRepository;
	
	@Autowired private QuestionService questionService;
	@Autowired private AnswerService answerService;

	
	@GetMapping("/question/list")
	public String list(Model model) {
//		List<Question> questionList = questionRepository.findAll();
//		List<Answer> answerList = answerRepository.findAll();
		List<Question> questionList = questionService.getList();
		List<Answer> answerList = answerService.getList();
		
		model.addAttribute("questionList", questionList);
		model.addAttribute("answerList", answerList);
		
		return "question_list";
	}
	
	
	
	@GetMapping(value = "/question/detail/{id}")
    public String detail(Model model, @PathVariable int id) {
		Question question = questionService.findById(id);
		List<Answer> answerList = question.getAnswerList();
		
		model.addAttribute("question", question);
		model.addAttribute("answerList", answerList);
		
        return "question_detail";
    }
	
}
