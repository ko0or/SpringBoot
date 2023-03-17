package com.cos.blog.question;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Integer> {

	// 반환타입 findBy컬럼명
	// 해당 컬럼에 일치하는 내용이 있는지 확인후, 일치하는 내용을 리턴해줌
	
	
	// List<Question> findBySubject(String subject);
	
	// 일치하는 값이 1개일때만 요걸 쓰고,  여러개라면 List 타입을 써야함
	
    Question findBySubject(String subject);
    Question findBySubjectAndContent(String subject, String content);
    List<Question> findBySubjectLike(String subject);

}
