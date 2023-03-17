package com.cos.blog.answer;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class AnswerForm {

	@NotEmpty(message = "댓글내용을 입력해주세요")
	private String content;
}
