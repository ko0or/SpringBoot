package com.cos.blog;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cos.blog.answer.Answer;
import com.cos.blog.answer.AnswerRepository;
import com.cos.blog.question.Question;
import com.cos.blog.question.QuestionRepository;

import jakarta.transaction.Transactional;

@SpringBootTest
class New20230313ApplicationTests {
	
	@Autowired
	private QuestionRepository questionRepository;
	@Autowired
	private AnswerRepository answerRepository;

	@Test
	@Transactional
	void contextLoads() {
		
		// 객체선언 : Question
		Question q;
		List<Question> listQ;
		Optional<Question> optionalQ;
				
		
		
		
//		 // 객체선언 : Answer
//		Answer a = new Answer().builder()
//													 .content("당근빠따죠")
//													 .createDate(LocalDateTime.now())
//													 .question(q) // question의 pk --> answer의 fk
//													 .build();
//		answerRepository.save(a);
		
		
		// ID가 2인걸 찾음
		q = questionRepository.findById(2).orElseThrow();
		
		// id (PK)가 2인 데이터베이스를 갖고온다
		//  PK값 2와 연결된 FK 값들을 찾는다
		List<Answer> answerList = q.getAnswerList();
		
		// PK 2와 연결된 FK 정보갯수를 예상치와 비교
		assertEquals(4, answerList.size());
		
		
//		listQ = questionRepository.findAll();
//		assertEquals(3, listQ.size(), 1);
		
//		q = questionRepository.findById(4).orElseThrow();
//		questionRepository.deleteById(q.getId());
		
		
//		 findBy컬럼명  써보기
//		 q = questionRepository.findBySubject("코딩일지가 무엇인가요?");
//		 assertEquals( "코딩일지에 대해서 알고 싶습니다.", q.getContent() );
		
		
		
		
//		 findById써보기
//		optionalQ = questionRepository.findById(1);
//		try {
//			q = optionalQ.get();
//			assertEquals(q.getSubject(), "코딩일지가 무엇인가요?");
//		} catch (Exception e) {
//			System.out.println(e);
//		}
//		
		
//		검증하기		 
//		List<Question> getQuestions = questionRepository.findAll();
//		assertEquals(4, getQuestions.size()); // getQuestions 사이즈가 4여야함
//		
//		q = getQuestions.get(0);
//		assertEquals(q.getSubject(), "코딩일지가 무엇인가요?"); // q.getSubject() 내용이 "코딩일지가 무엇인가요?" 여야한다
		
		
		
		
				
//		// 객체생성 + 할당
//		q = new Question().builder()
//				.subject("코딩일지가 무엇인가요?")
//				.content("코딩일지에 대해서 알고 싶습니다.")
//				.createDate( LocalDateTime.now() )
//				.build();
//		questionRepository.save(q);
//		
//		
//		 // 객체생성 + 할당
//		q = new Question().builder()
//										 .subject("IDENTITY 속성에 따라 숫자그 증가하나요?")
//										 .content("증가되었는지 보고싶습니다 !")
//										 .createDate( LocalDateTime.now() )
//										 .build();		
//		questionRepository.save(q);
//		
//		 q = new Question(null, "서브젝트", "콘텐츠", LocalDateTime.now(), null );
//		 questionRepository.save(q);
		
	}

}
