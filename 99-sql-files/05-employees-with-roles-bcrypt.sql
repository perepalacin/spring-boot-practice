DROP TABLE IF EXISTS `books`;
DROP TABLE IF EXISTS `authors`;

CREATE TABLE `authors` (
	`id` BIGINT NOT NULL AUTO_INCREMENT,
    `name` TEXT,
    `age` INT,
    PRIMARY KEY (`id`)
);

CREATE TABLE `books` (
	`isbn` TEXT NOT NULL,
    `title` TEXT,
    `author_id` BIGINT,
    PRIMARY KEY (`isbn`),
    CONSTRAINT `fk_author` FOREIGN KEY (`author_id`) REFERENCES `authors`(`id`)
);