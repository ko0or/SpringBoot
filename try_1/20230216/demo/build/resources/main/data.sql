-- 게시글 더미
INSERT INTO article(id, title, content) VALUES (1, '첫번째 글', 'data.sql 파일에 의해 자동생성되었습니다. [1]')
INSERT INTO article(id, title, content) VALUES (2, '두번째 글', 'data.sql 파일에 의해 자동생성되었습니다. [2]')
INSERT INTO article(id, title, content) VALUES (3, '세번째 글', 'data.sql 파일에 의해 자동생성되었습니다. [3]')

-- 첫번째 게시글의 댓글 더미
INSERT INTO comment(id, nickname, body, article_id) VALUES (1, '사람', '안녕하세요', 1)

-- 두번째 게시글의 댓글 더미
INSERT INTO comment(id, nickname, body, article_id) VALUES (2, '강아지', '멍멍', 2)
INSERT INTO comment(id, nickname, body, article_id) VALUES (3, '고양이', '냐옹', 2)


-- 세번째 게시글의 댓글 더미
INSERT INTO comment(id, nickname, body, article_id) VALUES (4, '사자', '첫번째 댓글', 3)
INSERT INTO comment(id, nickname, body, article_id) VALUES (5, '기린', '두번째 댓글', 3)
INSERT INTO comment(id, nickname, body, article_id) VALUES (6, '늑대', '세번째 댓글', 3)



