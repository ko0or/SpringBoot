### 메모 ✍

💡 -> return "redirect: <주소>";
  해당 주소로 새로고침한다 ( 겟매핑으로 사용하면, 다른 주소로 이동시켜버릴 수 있음 )
＠ 혹은 return "forward: <주소>, 기존 요청값들이 유지\된 상태로 URL 이동

💡 -> 타임리프 문법 ( th:each , th:text )
  // th:each , 배열요소를 하나씩 가져온다
  // 대상 : ${questionList} ,  받아오는 변수 : question
  // 사용할땐 question 에 콤마를 찍고 사용하면 된다. ( questionList를 question으로 하나씩 꺼내온다 )
  <tr th:each="question : ${questionList}">
      <td th:text="${question.id}"></td>
      <td th:text="${question.subject}"></td>
      <td th:text="${question.createDate}"></td>
  </tr>


💡 -> 타임리프 문법 ( th:href )
  <a th:href="@{|/question/detail/${question.id}|}" th:text="${question.subject}"></a>
  th:href=" " 따움표 안에 내용을 넣어주면된다. 그런데 보면 중괄호 시작과 끝에 | 가 들어가있는 모습을 볼 수 있다
  /question/detail/ 주소 뒤에 ${question.id} 를 넣었는데
  @ 이와같이  문자열 + 변수를 함께 사용해야된다면 {} 시작과끝에 | 를 넣어주면 된다
  @ 문자열과 변수를 함께 사용해야될땐 중괄호 {} 시작과끝에 | 를 넣어야한다


💡 -> 타임리프 문법 ( th:text   그리고   th:href )
  문자열과 연결할때  th:text는  " " 의 시작과 끝에 | 를 넣어주고
		  th:href는 "@{  }" 중괄호의 시작과 끝에 넣는다는 차이가 있다


💡 -> 타임리프 문법 ( 조건문 )
  th:if="${lists.size(변수명) > 0}" 변수명크기가 0 보다 클경우 해당 요소의 시작과 끝을 보여줌

@ 같이알아두기 :  th:unless="${#lists.size(answerList) > 0}" 는 ,  0 보다 크지않을경우
