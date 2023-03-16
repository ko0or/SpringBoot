# 타임리프에 날짜 포맷팅도 지원된다  
${#temporals.format(  대상모델명.createDate, 'yyyy.MM.dd'  ) } 
<td th:text="${question.createDate}"></td>  

<td th:text="${#temporals.format(question.createDate, 'yyyy.MM.dd')}"></td>



# @Valid 어노테이션과  BindingResult 를 이용한 검증해보기
포스트매핑이 있는 메소드의 매개변수에    @Valid AnswerForm answerForm, BindingResult bindingResult 를 적어준다 <br>
이때 어노테이션이 항상 우선순위에 있어야한다 (아닐경우, 에러가 발생) <br><br>

에러가 발생하면 기존의 페이지를 다시보게해주면 되고
if (bindingResult.hasErrors()) { 
            model.addAttribute("question", question);
            return "question_detail";
        }

값이 날라가지않게 폼 태그중 정보를 전달받을 태그에  th:field="*{content}" 속성&속성값을 추가해주면 끝





# 검증실패했을경우,  에러 메시지 띄우기
  <form th:action="@{|/answer/create/${question.id}|}" th:object="${answerForm}" method="post" class="my-3">



  th:object="${answerForm}" 내용과 아래 내용만 있으면된다.



        <div class="alert alert-danger" role="alert" th:if="${#fields.hasAnyErrors()}">

            <div th:each="err : ${#fields.allErrors()}" th:text="${err}" />

        </div>

