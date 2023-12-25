DROP TABLE IF EXISTS `members`;
DROP TABLE IF EXISTS `settings`;
DROP TABLE IF EXISTS `questions`;
DROP TABLE IF EXISTS `answers`;
DROP TABLE IF EXISTS `onboardings`;


CREATE TABLE `members`
(
    `member_id`   bigint auto_increment primary key,
    `social_id`   varchar(300) NOT NULL,
    `social_type` varchar(30)  NOT NULL,
    `member_role` varchar(30)  NOT NULL,
    `fcm_token`   varchar(300) NULL,
    `created_at`  datetime     NOT NULL,
    `modified_at` datetime     NOT NULL
);

CREATE TABLE `settings`
(
    `setting_id`          bigint auto_increment primary key,
    `member_id`           bigint   NOT NULL,
    `notification_status` boolean  NOT NULL,
    `created_at`          datetime NOT NULL,
    `modified_at`         datetime NOT NULL
);

CREATE TABLE `questions`
(
    `question_id`      bigint auto_increment primary key,
    `element_type`     varchar(30)  NULL,
    `question_title`   varchar(100) NULL,
    `question_content` varchar(300) NULL,
    `created_at`       datetime     NOT NULL,
    `modified_at`      datetime     NOT NULL
);

CREATE TABLE `answers`
(
    `answer_id`      bigint auto_increment primary key,
    `member_id`      bigint   NOT NULL,
    `question_id`    bigint   NOT NULL,
    `answer_content` text     NULL,
    `is_public`      boolean  NULL,
    `created_at`     datetime NOT NULL,
    `modified_at`    datetime NOT NULL
);

CREATE TABLE `onboardings`
(
    `onboarding_id` bigint auto_increment primary key,
    `member_id`     bigint      NOT NULL,
    `nickname`      varchar(30) NOT NULL,
    `created_at`    datetime    NOT NULL,
    `modified_at`   datetime    NOT NULL
);
