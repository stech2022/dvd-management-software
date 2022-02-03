DROP TABLE IF EXISTS `tokens`;
DROP TABLE IF EXISTS `orders`;
DROP TABLE IF EXISTS `shopping_card`;
DROP TABLE IF EXISTS `dvd`;
DROP TABLE IF EXISTS `users`;

CREATE TABLE `users`
(
    `id`                 int NOT NULL AUTO_INCREMENT,
    `username`           varchar(255) DEFAULT NULL,
    `password`           varchar(255) DEFAULT NULL,
    `firstName`          varchar(255) DEFAULT NULL,
    `lastName`           varchar(255) DEFAULT NULL,
    `role`               int          DEFAULT NULL,
    `address`            varchar(255) DEFAULT NULL,
    `cardType`           varchar(255) DEFAULT NULL,
    `cardNumber`         varchar(255) DEFAULT NULL,
    `cardExpirationDate` varchar(255) DEFAULT NULL,
    `cardCVV`            varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
);

INSERT INTO `users`
VALUES (1, 'User 01', 'password', 'John', 'Doe', 1, 'Address 00', 'Credit', '111 111 111 1111', '12/22', '111');
INSERT INTO `users`
VALUES (2, 'User 02', 'password', 'John', 'Doe', 1, 'Address 00', 'Credit', '111 111 111 1111', '12/22', '111');
INSERT INTO `users`
VALUES (3, 'User 03', 'password', 'John', 'Doe', 1, 'Address 00', 'Credit', '111 111 111 1111', '12/22', '111');
INSERT INTO `users`
VALUES (4, 'User 04', 'password', 'John', 'Doe', 1, 'Address 00', 'Credit', '111 111 111 1111', '12/22', '111');
INSERT INTO `users`
VALUES (5, 'User 05', 'password', 'John', 'Doe', 1, 'Address 00', 'Credit', '111 111 111 1111', '12/22', '111');
INSERT INTO `users`
VALUES (6, 'User 06', 'password', 'John', 'Doe', 1, 'Address 00', 'Credit', '111 111 111 1111', '12/22', '111');
INSERT INTO `users`
VALUES (7, 'User 07', 'password', 'John', 'Doe', 1, 'Address 00', 'Credit', '111 111 111 1111', '12/22', '111');
INSERT INTO `users`
VALUES (8, 'User 08', 'password', 'John', 'Doe', 1, 'Address 00', 'Credit', '111 111 111 1111', '12/22', '111');
INSERT INTO `users`
VALUES (9, 'User 09', 'password', 'John', 'Doe', 1, 'Address 00', 'Credit', '111 111 111 1111', '12/22', '111');

CREATE TABLE `dvd`
(
    `id`          int NOT NULL AUTO_INCREMENT,
    `title`       varchar(45) DEFAULT NULL,
    `actors`      varchar(45) DEFAULT NULL,
    `director`    varchar(45) DEFAULT NULL,
    `produceDate` varchar(45) DEFAULT NULL,
    `duration`    int         DEFAULT NULL,
    `languages`   varchar(45) DEFAULT NULL,
    `subtitles`   varchar(45) DEFAULT NULL,
    `category`    varchar(45) DEFAULT NULL,
    `price`       float       DEFAULT NULL,
    `units`       int         DEFAULT NULL,
    PRIMARY KEY (`id`)
);

INSERT INTO `dvd`
VALUES (1, 'Title 01', 'Actor 1, Actor 2', 'Director', '01/01/2022', 120, 'English', 'Greek, English', 'sciFy', '19.00',
        '45');
INSERT INTO `dvd`
VALUES (2, 'Title 02', 'Actor 1, Actor 2', 'Director', '01/01/2022', 123, 'English', 'Greek, English', 'sciFy', '19.00',
        '45');
INSERT INTO `dvd`
VALUES (3, 'Title 03', 'Actor 1, Actor 2', 'Director', '01/01/2022', 112, 'English', 'Greek, English', 'sciFy', '19.00',
        '45');
INSERT INTO `dvd`
VALUES (4, 'Title 04', 'Actor 1, Actor 2', 'Director', '01/01/2022', 150, 'English', 'Greek, English', 'sciFy', '19.00',
        '45');
INSERT INTO `dvd`
VALUES (5, 'Title 05', 'Actor 1, Actor 2', 'Director', '01/01/2022', 160, 'English', 'Greek, English', 'sciFy', '19.00',
        '45');
INSERT INTO `dvd`
VALUES (6, 'Title 06', 'Actor 1, Actor 2', 'Director', '01/01/2022', 101, 'English', 'Greek, English', 'sciFy', '19.00',
        '45');
INSERT INTO `dvd`
VALUES (7, 'Title 07', 'Actor 1, Actor 2', 'Director', '01/01/2022', 122, 'English', 'Greek, English', 'sciFy', '19.00',
        '45');
INSERT INTO `dvd`
VALUES (8, 'Title 08', 'Actor 1, Actor 2', 'Director', '01/01/2022', 134, 'English', 'Greek, English', 'sciFy', '19.00',
        '45');
INSERT INTO `dvd`
VALUES (9, 'Title 09', 'Actor 1, Actor 2', 'Director', '01/01/2022', 145, 'English', 'Greek, English', 'sciFy', '19.00',
        '45');
INSERT INTO `dvd`
VALUES (10, 'Title 10', 'Actor 1, Actor 2', 'Director', '01/01/2022', 111, 'English', 'Greek, English', 'sciFy',
        '19.00', '45');
INSERT INTO `dvd`
VALUES (11, 'Title 11', 'Actor 1, Actor 2', 'Director', '01/01/2022', 105, 'English', 'Greek, English', 'sciFy',
        '19.00', '45');
INSERT INTO `dvd`
VALUES (12, 'Title 11', 'Actor 1, Actor 2', 'Director', '01/01/2022', 124, 'English', 'Greek, English', 'sciFy',
        '19.00', '45');
INSERT INTO `dvd`
VALUES (13, 'Title 12', 'Actor 1, Actor 2', 'Director', '01/01/2022', 120, 'English', 'Greek, English', 'sciFy',
        '19.00', '45');
INSERT INTO `dvd`
VALUES (14, 'Title 13', 'Actor 1, Actor 2', 'Director', '01/01/2022', 129, 'English', 'Greek, English', 'sciFy',
        '19.00', '45');
INSERT INTO `dvd`
VALUES (15, 'Title 14', 'Actor 1, Actor 2', 'Director', '01/01/2022', 180, 'English', 'Greek, English', 'sciFy',
        '19.00', '45');

CREATE TABLE `shopping_card`
(
    `id`            int NOT NULL AUTO_INCREMENT,
    `user_id`       int          DEFAULT NULL,
    `dvd_id`        int          DEFAULT NULL,
    `amount`        int          DEFAULT NULL,
    `creation_date` varchar(255) DEFAULT NULL,
    `state`         varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `shopping_card_users_fk` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO `shopping_card`
VALUES (1, 1, 1, 100, '01/01/2022', 'completed');
INSERT INTO `shopping_card`
VALUES (2, 2, 2, 200, '01/01/2022', 'completed');
INSERT INTO `shopping_card`
VALUES (3, 3, 3, 300, '01/01/2022', 'completed');
INSERT INTO `shopping_card`
VALUES (4, 4, 4, 400, '01/01/2022', 'completed');
INSERT INTO `shopping_card`
VALUES (5, 5, 5, 500, '01/01/2022', 'completed');
INSERT INTO `shopping_card`
VALUES (6, 6, 6, 600, '01/01/2022', 'completed');
INSERT INTO `shopping_card`
VALUES (7, 7, 7, 700, '01/01/2022', 'completed');
INSERT INTO `shopping_card`
VALUES (8, 8, 8, 800, '01/01/2022', 'completed');
INSERT INTO `shopping_card`
VALUES (9, 9, 9, 900, '01/01/2022', 'completed');

CREATE TABLE `orders`
(
    `id`               int NOT NULL AUTO_INCREMENT,
    `address`          varchar(255) DEFAULT NULL,
    `shopping_card_id` int          DEFAULT NULL,
    `user_id`          int          DEFAULT NULL,
    `state`            varchar(255) DEFAULT NULL,
    `creation_date`    varchar(255) DEFAULT NULL,
    `completion_date`  varchar(255) DEFAULT NULL,
    `dvd_ids`          varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `orders_shopping_card_fk` FOREIGN KEY (`shopping_card_id`) REFERENCES `shopping_card` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT `orders_user_fk` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO `orders`
VALUES (1, 'Address 01', 1, 1, 'completed', '01/01/2022', '01/01/2022', '1,2,3,4,5,6');
INSERT INTO `orders`
VALUES (2, 'Address 02', 2, 1, 'completed', '01/01/2022', '01/01/2022', '1,2,3,4,5,6');
INSERT INTO `orders`
VALUES (3, 'Address 03', 3, 2, 'completed', '01/01/2022', '01/01/2022', '1,2,3,4,5,6');
INSERT INTO `orders`
VALUES (4, 'Address 04', 4, 2, 'completed', '01/01/2022', '01/01/2022', '1,2,3,4,5,6');
INSERT INTO `orders`
VALUES (5, 'Address 05', 5, 3, 'completed', '01/01/2022', '01/01/2022', '1,2,3,4,5,6');
INSERT INTO `orders`
VALUES (6, 'Address 06', 6, 3, 'completed', '01/01/2022', '01/01/2022', '1,2,3,4,5,6');
INSERT INTO `orders`
VALUES (7, 'Address 07', 7, 4, 'completed', '01/01/2022', '01/01/2022', '1,2,3,4,5,6');
INSERT INTO `orders`
VALUES (8, 'Address 08', 8, 4, 'completed', '01/01/2022', '01/01/2022', '1,2,3,4,5,6');
INSERT INTO `orders`
VALUES (9, 'Address 09', 9, 5, 'completed', '01/01/2022', '01/01/2022', '1,2,3,4,5,6');

CREATE TABLE `tokens`
(
    `id`      int NOT NULL AUTO_INCREMENT,
    `hash`    varchar(255) DEFAULT NULL,
    `user_id` int          DEFAULT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `token_user_fk` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
)