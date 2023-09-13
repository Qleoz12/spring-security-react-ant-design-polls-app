ALTER TABLE `subject_poll_professor`
MODIFY COLUMN `teacher_id` bigint(20) NOT NULL,
DROP FOREIGN KEY `subject_poll_professor_ibfk_3`,
ADD CONSTRAINT `subject_poll_professor_ibfk_3` FOREIGN KEY (`teacher_id`) REFERENCES `users` (`id`);
