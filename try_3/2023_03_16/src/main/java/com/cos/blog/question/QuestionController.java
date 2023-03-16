package com.cos.blog.question;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cos.blog.answer.Answer;
import com.cos.blog.answer.AnswerForm;
import com.cos.blog.answer.AnswerRepository;
import com.cos.blog.answer.AnswerService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/question")
public class QuestionController {
	
	@Autowired	private QuestionRepository questionRepository;
	@Autowired	private AnswerRepository answerRepository;
	
	@Autowired private QuestionService questionService;
	@Autowired private AnswerService answerService;

	
	@GetMapping("/list")
	public String list(Model model) {
//		List<Question> questionList = questionRepository.findAll();
//		List<Answer> answerList = answerRepository.findAll();
		List<Question> questionList = questionService.getList();
		List<Answer> answerList = answerService.getList();
		
		model.addAttribute("questionList", questionList);
		model.addAttribute("answerList", answerList);
		
		return "question_list";
	}
	
	
	
	@GetMapping(value = "/detail/{id}")
    public String detail(AnswerForm answerForm, Model model, @PathVariable int id) {
		Question question = questionService.findById(id);
		List<Answer> answerList = question.getAnswerList();
		
		model.addAttribute("question", question);
		model.addAttribute("answerList", answerList);
		
        return "question_detail";
    }
	
	
	
	// questionCreate() 메소드 오버로딩
	// 해당 메소드에 QuestionForm(QuestionForm.class) 안넣으면 오류발생함 
	@GetMapping("/create")
	public String questionCreate(QuestionForm questionForm) {
		return "question_form";
	}
	
	@PostMapping("/create")
	public String questionCreate(@Valid QuestionForm questionForm, BindingResult bindingResult) {
	// Valid 는 BindingResult 보다 우선순위에 있어야한다 ( 그렇지않다면 에러발생 )
	
		// subject , content 입력누락여부 검사 : 누락시 question_form.html 파일로
		if (bindingResult.hasErrors()) { return "question_form"; }
		
		// 입력누락이 아니라면,  레파지토리로 저장한후 list 페이지로 이동
		questionService.createQuestion(questionForm.getSubject(), questionForm.getContent());
		return "redirect:/question/list";
		
	}
//	@PostMapping("/create")
//	public String questionCreate(@RequestParam String subject, 
//															@RequestParam String content									
//															) 
//	{
//		questionService.createQuestion(subject, content);
//		return "redirect:/";
//	}
	
}
