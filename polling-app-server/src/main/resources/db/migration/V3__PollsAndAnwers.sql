CREATE TABLE `PollAnswers` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `choice_id` bigint(20) NOT NULL,
  `poll_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK8um9h2wxsdjrgx3rjjwvny676` (`poll_id`,`choice_id`),
  KEY `FKomskymhxde3qq9mcukyp1puio` (`choice_id`),
  KEY `FKli4uj3ic2vypf5pialchj925e` (`poll_id`),
  CONSTRAINT `FK7trt3uyihr4g13hva9d31puxg` FOREIGN KEY (`poll_id`) REFERENCES `polls` (`id`),
  CONSTRAINT `FKomskymhxde3qq9mcukyp1puio` FOREIGN KEY (`choice_id`) REFERENCES `choices` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8