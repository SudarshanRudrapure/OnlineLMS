SHOW DATABASES;
USE online_lms;
SHOW TABLES;
SELECT * FROM courses;

USE online_lms;
DESCRIBE users;
ALTER TABLE users
ADD COLUMN password VARCHAR(255) NOT NULL;

DESCRIBE users;


USE online_lms;
DESCRIBE users;

ALTER TABLE users CHANGE password password_hash VARCHAR(255) NOT NULL;

DESCRIBE users;
ALTER TABLE users DROP COLUMN password_hash;


ALTER TABLE users 
ADD COLUMN password_hash VARCHAR(255) NOT NULL AFTER email;
