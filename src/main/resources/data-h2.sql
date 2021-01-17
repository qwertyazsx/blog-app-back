INSERT INTO posts (post_no, content, title, create_date, update_date) VALUES (1, '내용1', '포스트 1', SYSDATE, SYSDATE);
INSERT INTO posts (post_no, content, title, create_date, update_date) VALUES (2, '내용2', '포스트 2', SYSDATE, SYSDATE);
INSERT INTO posts (post_no, content, title, create_date, update_date) VALUES (3, '내용3', '포스트 3', SYSDATE, SYSDATE);
INSERT INTO posts (post_no, content, title, create_date, update_date) VALUES (4, '내용4', '포스트 4', SYSDATE, SYSDATE);
INSERT INTO posts (post_no, content, title, create_date, update_date) VALUES (5, '내용5', '포스트 5', SYSDATE, SYSDATE);

INSERT INTO tag (tag_no, name) VALUES (1, '태그1');
INSERT INTO tag (tag_no, name) VALUES (2, '태그2');
INSERT INTO tag (tag_no, name) VALUES (3, '태그3');
INSERT INTO tag (tag_no, name) VALUES (4, '태그4');
INSERT INTO tag (tag_no, name) VALUES (5, '태그5');
INSERT INTO tag (tag_no, name) VALUES (6, '태그6');
INSERT INTO tag (tag_no, name) VALUES (7, '태그7');
INSERT INTO tag (tag_no, name) VALUES (8, '태그8');
INSERT INTO tag (tag_no, name) VALUES (9, '태그9');

INSERT INTO posts_tag (id, post_no, tag_no) VALUES (1, 1, 1);
INSERT INTO posts_tag (id, post_no, tag_no) VALUES (2, 1, 2);
INSERT INTO posts_tag (id, post_no, tag_no) VALUES (3, 1, 3);
INSERT INTO posts_tag (id, post_no, tag_no) VALUES (4, 2, 2);
INSERT INTO posts_tag (id, post_no, tag_no) VALUES (5, 2, 3);
INSERT INTO posts_tag (id, post_no, tag_no) VALUES (6, 2, 4);
INSERT INTO posts_tag (id, post_no, tag_no) VALUES (7, 3, 4);
INSERT INTO posts_tag (id, post_no, tag_no) VALUES (8, 3, 5);
INSERT INTO posts_tag (id, post_no, tag_no) VALUES (9, 3, 1);
INSERT INTO posts_tag (id, post_no, tag_no) VALUES (10, 4, 6);
INSERT INTO posts_tag (id, post_no, tag_no) VALUES (11, 4, 7);
INSERT INTO posts_tag (id, post_no, tag_no) VALUES (12, 4, 8);