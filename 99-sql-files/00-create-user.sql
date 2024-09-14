DROP USER if exists 'springstudent'@'localhost';

CREATE USER 'springstudent'@'localhost' IDENTIFIED by 'springstudent';

GRANT ALL PRIVILEGES ON * . * TO 'springstudent'@'localhost';