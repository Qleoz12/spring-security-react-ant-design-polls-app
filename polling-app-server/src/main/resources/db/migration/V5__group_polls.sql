CREATE TABLE polls_group (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;


ALTER TABLE polls
ADD COLUMN `poll_group_id` bigint(20) DEFAULT NULL,
ADD CONSTRAINT `fk_poll_group`
  FOREIGN KEY (`poll_group_id`)
  REFERENCES polling_app.polls_group (`id`);
