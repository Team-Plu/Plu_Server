DROP TABLE IF EXISTS `members`;
DROP TABLE IF EXISTS `settings`;
DROP TABLE IF EXISTS `questions`;
DROP TABLE IF EXISTS `answers`;
DROP TABLE IF EXISTS `onboardings`;
DROP TABLE IF EXISTS `likes`;


CREATE TABLE `members`
(
    `member_id`   bigint auto_increment primary key,
    `social_id`   varchar(300) NOT NULL,
    `social_type` varchar(30)  NOT NULL,
    `member_role` varchar(30)  NOT NULL,
    `fcm_token`   varchar(300) NULL,
    `setting_id`  bigint       NOT NULL,
    `created_at`  datetime     NOT NULL,
    `modified_at` datetime     NOT NULL
);

CREATE TABLE `settings`
(
    `setting_id`          bigint auto_increment primary key,
    `notification_status` boolean  NOT NULL,
    `created_at`          datetime NOT NULL,
    `modified_at`         datetime NOT NULL
);

CREATE TABLE `questions`
(
    `question_id`      bigint auto_increment primary key,
    `element_type`     varchar(30)  NOT NULL,
    `question_date`    datetime     NOT NULL,
    `question_title`   varchar(100) NOT NULL,
    `question_content` varchar(300) NOT NULL,
    `exposed_at`       datetime     NOT NULL,
    `created_at`       datetime     NOT NULL,
    `modified_at`      datetime     NOT NULL,
    constraint unique ink01_questions(exposed_at)
);

CREATE TABLE `answers`
(
    `answer_id`      bigint auto_increment primary key,
    `member_id`      bigint   NOT NULL,
    `question_id`    bigint   NOT NULL,
    `answer_content` text     NOT NULL,
    `is_public`      boolean  NOT NULL,
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

CREATE TABLE `likes`
(
    `like_id`     bigint auto_increment primary key,
    `question_id` bigint   NOT NULL,
    `answer_id`   bigint   NOT NULL,
    `member_id`   bigint   NOT NULL,
    `created_at`  datetime NOT NULL,
    `modified_at` datetime NOT NULL
);