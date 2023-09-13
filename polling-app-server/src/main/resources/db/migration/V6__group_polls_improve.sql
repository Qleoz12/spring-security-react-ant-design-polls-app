ALTER TABLE subject_poll_professor
ADD COLUMN `poll_group_id` bigint(20) DEFAULT 1,
ADD CONSTRAINT `fk_poll_group`
FOREIGN KEY (`poll_group_id`) REFERENCES polls_group (`id`);
