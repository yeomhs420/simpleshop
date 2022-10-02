INSERT INTO user(id, userid, user_password, username, user_email, user_gender, balance) VALUES (1, 'yeomhs', '1234', '염형섭', 'yeomhs420@naver.com', '남자', 0);

INSERT INTO product(id, user_id, name, amount, price, image_url) VALUES (1, 1, 'beige bag', 1, 69000, 'https://bakey-api.codeit.kr/files/629/images/beige_bag.jpg');

INSERT INTO bbs(id, title, user_id, content) values (1, '제목', 1, '내용입니다.');

INSERT INTO comment(id, bbs_id, nickname, body) VALUES (1, 1, 'yeom', 'God');
