-- ✅ 1. 유저 테이블 생성
DROP TABLE IF EXISTS `user`;
DROP TABLE IF EXISTS `game_room`;
DROP TABLE IF EXISTS `participant`;
DROP TABLE IF EXISTS `copy_of_personal_score`;

CREATE TABLE `users` (
                        user_id INT NOT NULL AUTO_INCREMENT,
                        username VARCHAR(100) NOT NULL,
                        password VARCHAR(100) NOT NULL,
                        email VARCHAR(150) NOT NULL,
                        PRIMARY KEY (user_id)
);
 

-- ✅ 2. 게임룸 테이블 생성
CREATE TABLE game_room (
                           room_id INT NOT NULL AUTO_INCREMENT,
                           room_name VARCHAR(25) NOT NULL,
                           max_member INT NOT NULL,
                           current_member INT NOT NULL,
                           status VARCHAR(20) NOT NULL,
                           created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                           level VARCHAR(25) NOT NULL,
                           content VARCHAR(255) NOT NULL,
                           language TINYINT NOT NULL,
                           PRIMARY KEY (room_id)
);

-- ✅ 3. 참가자 테이블 생성
CREATE TABLE participant (
                             room_id INT NOT NULL,
                             user_id INT NOT NULL,
                             join_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                             score INT NOT NULL DEFAULT 0,
                             is_host BOOLEAN NOT NULL,
                             is_ready BOOLEAN NOT NULL,
                             PRIMARY KEY (room_id, user_id),
                             CONSTRAINT fk_room_id FOREIGN KEY (room_id) REFERENCES game_room (room_id),
                             CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES `user` (user_id)
);

-- ✅ 4. 개인 점수 테이블 생성
CREATE TABLE copy_of_personal_score (
                                        user_id INT NOT NULL,
                                        total_plays INT DEFAULT 0,
                                        total_score_sum INT DEFAULT 0,
                                        high_score INT DEFAULT 0,
                                        PRIMARY KEY (use`user`r_id),
                                        CONSTRAINT fk_user_id_score FOREIGN KEY (user_id) REFERENCES `user` (user_id)
);







SHOW DATABASE;


