-- sql/online_lms.sql
DROP DATABASE IF EXISTS online_lms;
CREATE DATABASE online_lms CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
USE online_lms;


-- App user (optional — you can also use root during testing)
CREATE USER IF NOT EXISTS 'lms_user'@'localhost' IDENTIFIED BY 'lms_pass';
GRANT ALL PRIVILEGES ON online_lms.* TO 'lms_user'@'localhost';
FLUSH PRIVILEGES;


-- Tables
CREATE TABLE users (
id INT PRIMARY KEY AUTO_INCREMENT,
name VARCHAR(100) NOT NULL,
email VARCHAR(120) NOT NULL UNIQUE,
password_hash VARCHAR(255) NOT NULL,
created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


CREATE TABLE courses (
id INT PRIMARY KEY AUTO_INCREMENT,
title VARCHAR(150) NOT NULL,
description TEXT,
created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


CREATE TABLE enrollments (
user_id INT,
course_id INT,
enrolled_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
PRIMARY KEY (user_id, course_id),
FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
FOREIGN KEY (course_id) REFERENCES courses(id) ON DELETE CASCADE
);


CREATE TABLE contests (
id INT PRIMARY KEY AUTO_INCREMENT,
name VARCHAR(150) NOT NULL,
start_time DATETIME NOT NULL,
end_time DATETIME NOT NULL
);


CREATE TABLE submissions (
id INT PRIMARY KEY AUTO_INCREMENT,
user_id INT NOT NULL,
contest_id INT NOT NULL,
score INT NOT NULL DEFAULT 0,
submitted_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
FOREIGN KEY (contest_id) REFERENCES contests(id) ON DELETE CASCADE
);


-- Seed data
INSERT INTO courses(title, description) VALUES
('Java Basics', 'Intro to Java'),
('Web Dev', 'HTML/CSS/JS fundamentals'),
('Data Structures', 'Core DS in Java');


INSERT INTO contests(name, start_time, end_time) VALUES
('August Challenge', NOW(), DATE_ADD(NOW(), INTERVAL 7 DAY)),
('DSA Sprint', NOW(), DATE_ADD(NOW(), INTERVAL 3 DAY));