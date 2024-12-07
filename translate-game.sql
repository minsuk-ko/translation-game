DROP TABLE IF EXISTS `user`;

CREATE TABLE User (
   userId INT NOT NULL AUTO_INCREMENT,
   username VARCHAR(255) NOT NULL,
   password VARCHAR(255) NOT NULL,
   email VARCHAR(255) NOT NULL,
   PRIMARY KEY (userId)
);



CREATE TABLE gameroom (
   roomId INT NOT NULL AUTO_INCREMENT,
   roomname VARCHAR(25) NOT NULL,
   maxMember INT NOT NULL,m
   currentMember INT NOT NULL,
   status VARCHAR(255) NOT NULL,
   createdAt TIMESTAMP NOT NULL,
   level VARCHAR(25) NOT NULL,
   content VARCHAR(255) NOT NULL,
   language TINYINT NOT NULL,
   PRIMARY KEY (roomId)
);

CREATE TABLE participant (
   roomId INT NOT NULL,
   userId INT NOT NULL,
   joinAt TIMESTAMP NOT NULL,
   score INT NOT NULL DEFAULT 0,
   isHost TINYINT(1) NOT NULL,
   isReady TINYINT(1) NOT NULL,
   PRIMARY KEY (roomId, userId),
   FOREIGN KEY (roomId) REFERENCES gameroom (roomId),
   FOREIGN KEY (userId) REFERENCES User (userId)
);

CREATE TABLE CopyOfpersonalScore (
   userId INT NOT NULL,
   totalPlays INT NULL,
   totalScoreSum INT NULL,
   highScore INT NULL,
   PRIMARY KEY (userId),
   FOREIGN KEY (userId) REFERENCES User (userId)
);







