DROP
DATABASE IF EXISTS `hotel_booking`;
CREATE
DATABASE `hotel_booking` CHARACTER SET 'utf8' COLLATE 'utf8_general_ci';

USE
`hotel_booking`;

DROP TABLE IF EXISTS `i18n`;
CREATE TABLE `i18n`
(
    `id`     int     NOT NULL AUTO_INCREMENT,
    `locale` varchar(5) NOT NULL,
    `name`   varchar(32) DEFAULT '',
    PRIMARY KEY (`id`),
    UNIQUE INDEX `i18n_locale_uindex`(`locale`)
);

DROP TABLE IF EXISTS `amenity`;
CREATE TABLE `amenity`
(
    `id`    int          NOT NULL AUTO_INCREMENT,
    `price` int DEFAULT 0,
    PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `amenity_i18n`;
CREATE TABLE `amenity_i18n`
(
    `id`         int       NOT NULL AUTO_INCREMENT,
    `amenity_id` int          NOT NULL,
    `i18n_id`    int       NOT NULL,
    `name`       varchar(255) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `amenity_i18n_uindex`(`amenity_id`, `i18n_id`),
    CONSTRAINT `amenity_i18n_id_fk` FOREIGN KEY (`amenity_id`) REFERENCES `amenity` (`id`) ON DELETE CASCADE,
    CONSTRAINT `i18n_id_fk` FOREIGN KEY (`i18n_id`) REFERENCES `i18n` (`id`) ON DELETE CASCADE
);

DROP TABLE IF EXISTS `room_type`;
CREATE TABLE `room_type`
(
    `id`          int          NOT NULL AUTO_INCREMENT,
    `occupancy`   int          NOT NULL,
    `image`       varchar(255) DEFAULT '',
    `size`        int          DEFAULT 0,
    `price`       int          DEFAULT 0,
    `rooms`       int          DEFAULT 0,
    `access`	  boolean	   DEFAULT false,
    PRIMARY KEY (`id`),
    INDEX  `occupancy_index`(`occupancy`)
);

DROP TABLE IF EXISTS `room_type_i18n`;
CREATE TABLE `room_type_i18n`
(
    `id`           int       NOT NULL AUTO_INCREMENT,
    `room_type_id` int          NOT NULL,
    `i18n_id`      int       NOT NULL,
    `name`         varchar(255) NOT NULL,
    `description`  varchar(512) DEFAULT '',
    PRIMARY KEY (`id`),
    UNIQUE INDEX `room_type_i18n_uindex`(`room_type_id`, `i18n_id`),
    CONSTRAINT `room_type_i18n_id_fk` FOREIGN KEY (`room_type_id`) REFERENCES `room_type` (`id`) ON DELETE CASCADE,
    CONSTRAINT `i18n_id_room_type_fk` FOREIGN KEY (`i18n_id`) REFERENCES `i18n` (`id`) ON DELETE CASCADE
);

DROP TABLE IF EXISTS `amenity_room_type`;
CREATE TABLE `amenity_room_type`
(
    `room_type_id` int NOT NULL,
    `amenity_id`   int NOT NULL,
    PRIMARY KEY (`room_type_id`, `amenity_id`),
    CONSTRAINT `amenity_id_fk` FOREIGN KEY (`amenity_id`) REFERENCES `amenity` (`id`) ON DELETE CASCADE,
    CONSTRAINT `room_type_amenity_id_fk` FOREIGN KEY (`room_type_id`) REFERENCES `room_type` (`id`) ON DELETE CASCADE
);

DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`
(
    `id`   int          NOT NULL AUTO_INCREMENT,
    `role` varchar(255) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `role_role_uindex`(`role`)
);

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`
(
    `id`          int          NOT NULL AUTO_INCREMENT,
    `username`    varchar(128) NOT NULL,
    `email`       varchar(128) NOT NULL,
    `password`    varchar(256) NOT NULL,
    `first_name`  varchar(128) DEFAULT '',
    `last_name`   varchar(128) DEFAULT '',
    `country`     varchar(128) DEFAULT '',
    `card_type`   varchar(64)  DEFAULT '',
    `card_number` varchar(64)  DEFAULT '',
    PRIMARY KEY (`id`),
    UNIQUE INDEX `user_email_uindex`(`email`),
    INDEX         `user_password_index`(`password`)
);

DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`
(
    `user_id` int NOT NULL,
    `role_id` int NOT NULL,
    UNIQUE INDEX `user_role_user_id__uindex`(`user_id`),
    CONSTRAINT `user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE,
    CONSTRAINT `role_id_fk` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE CASCADE
);

DROP TABLE IF EXISTS `booking`;
CREATE TABLE `booking`
(
    `id`           int  NOT NULL AUTO_INCREMENT,
    `room_type_id` int  NOT NULL,
    `user_id`      int  NOT NULL,
    `arrival_date` date NOT NULL,
    `depart_date`  date NOT NULL,
    `num_adults`   int  NOT NULL,
    `num_children` int DEFAULT 0,
    `price`        int  NOT NULL,
    PRIMARY KEY (`id`),
    INDEX          `booking_arrival_index`(`arrival_date`),
    INDEX          `booking_depart_index`(`depart_date`),
    CONSTRAINT `room_type_booking_id_fk` FOREIGN KEY (`room_type_id`) REFERENCES `room_type` (`id`) ON DELETE CASCADE,
    CONSTRAINT `booking_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE
);

DROP TABLE IF EXISTS `remembered_login`;
CREATE TABLE `remembered_login`
(
    `token_hash` varchar(64)  NOT NULL,
    `user_id`    int          NOT NULL,
    `expires_at` timestamp(0) NOT NULL,
    UNIQUE INDEX `remembered_login_token_hash_uindex`(`token_hash`),
    UNIQUE INDEX `remembered_login_user_id_uindex`(`user_id`),
    CONSTRAINT `remembered_login_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE
);