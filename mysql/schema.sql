-- 使用するデータベースを指定
USE myproject;

-- テーブル作成: customer2
CREATE TABLE customer2 (
  id int PRIMARY KEY NOT NULL AUTO_INCREMENT,
  login_id varchar(100) NOT NULL UNIQUE,
  password varchar(100) NOT NULL,
  role varchar(16) NOT NULL DEFAULT 'GENERAL'
);

-- テーブル作成: board2
CREATE TABLE board2 (
  id int PRIMARY KEY NOT NULL AUTO_INCREMENT,
  mydate datetime DEFAULT CURRENT_TIMESTAMP,
  login_id varchar(100) NOT NULL,
  contents varchar(100) NOT NULL,
  likes int NOT NULL DEFAULT '0',
  dislikes int NOT NULL DEFAULT '0'
);