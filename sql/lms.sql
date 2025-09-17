-- Use your app database
CREATE DATABASE IF NOT EXISTS online_lms
  CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;

-- Create an app-specific user
CREATE USER IF NOT EXISTS 'lms_user'@'localhost' IDENTIFIED BY 'lms_pass';

-- Give it access to your database
GRANT ALL PRIVILEGES ON online_lms.* TO 'lms_user'@'localhost';

FLUSH PRIVILEGES;

-- Sanity checks
USE online_lms;
SHOW TABLES;
DESCRIBE users;
SELECT DATABASE();  -- should show 'online_lms'
