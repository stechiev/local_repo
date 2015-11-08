CREATE TABLE `restaraunt` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) NOT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8

CREATE TABLE `dish` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `restaraunt_id` int(11) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `price` decimal(8,2) DEFAULT NULL,
  `active` tinyint(1) NOT NULL DEFAULT '1',
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `dish_restrnt_fk_idx` (`restaraunt_id`),
  CONSTRAINT `dish_restrnt_fk` FOREIGN KEY (`restaraunt_id`) REFERENCES `restaraunt` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8

CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `login` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `role_id` int(11) NOT NULL,
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_role_fk_idx` (`role_id`),
  CONSTRAINT `user_role_fk` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8

CREATE TABLE `question` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `active` tinyint(1) NOT NULL DEFAULT '0',
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated` timestamp NULL DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8

CREATE TABLE `vote` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `restaurant_id` int(11) NOT NULL,
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated` timestamp NULL DEFAULT NULL,
  `question_id` int(11) NOT NULL,
  `descr` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `vote_unique_idx` (`user_id`,`restaurant_id`,`question_id`),
  KEY `vote_user_fk_idx` (`user_id`),
  KEY `vote_restaurant_idx` (`restaurant_id`),
  KEY `vote_question_fk_idx` (`question_id`),
  CONSTRAINT `vote_question_fk` FOREIGN KEY (`question_id`) REFERENCES `question` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `vote_restaurant` FOREIGN KEY (`restaurant_id`) REFERENCES `restaraunt` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `vote_user_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8

create or replace view test.daily_stat (restaraunt_id,vote_count) as
select  v.restaurant_id, count(*) from vote v join question q on v.question_id = q.id where q.active=1 group by v.restaurant_id;


INSERT INTO `test`.`role` ( `id`,`name`) VALUES ('1','ADMIN');
INSERT INTO `test`.`role` ( `id`,`name`) VALUES ('3','USER');

INSERT INTO `test`.`user` (`name`, `login`, `password`, `role_id` ) VALUES ('Johhny', 'john', 'qwerty', '1');
INSERT INTO `test`.`user` (`name`, `login`, `password`, `role_id` ) VALUES ('Bobby', 'Bob', 'ytrewq', '3');
INSERT INTO `test`.`user` (`name`, `login`, `password`, `role_id` ) VALUES ('Mikey', 'Mike', '12345', '3');
INSERT INTO `test`.`user` (`name`, `login`, `password`, `role_id` ) VALUES ('Kenny', 'Ken', '54321', '3');


INSERT INTO `test`.`restaraunt` (`id`, `address`, `phone`, `name`) VALUES ('1', 'some street 5', '7926123456', 'Titty Twister');
INSERT INTO `test`.`restaraunt` (`id`, `address`, `phone`, `name`) VALUES ('3', 'Novigrad', '7916123456', 'Passiflora brothel');
INSERT INTO `test`.`restaraunt` (`id`, `address`, `phone`, `name`) VALUES ('7', 'bottom of the sea', '911', 'Krusty Krab');
INSERT INTO `test`.`restaraunt` (`id`, `address`, `phone`, `name`) VALUES ('13', 'another stupid address', '911', 'Coyote Ugly');
INSERT INTO `test`.`restaraunt` (`id`, `address`, `phone`, `name`) VALUES ('15', 'Mass Effect, Citadel', '911', 'Purgatory Bar');

INSERT INTO `test`.`dish` (`id`, `restaraunt_id`, `name`, `price`, `active`) VALUES ('1', '7', 'krabsburger', '2.00', '1');
INSERT INTO `test`.`dish` (`id`, `restaraunt_id`, `name`, `price`, `active`) VALUES ('3', '7', 'krabspizza', '4.00', '1');
INSERT INTO `test`.`dish` (`id`, `restaraunt_id`, `name`, `price`, `active`) VALUES ('9', '3', 'Grilled Pork', '2.00', '1');
INSERT INTO `test`.`dish` (`id`, `restaraunt_id`, `name`, `price`, `active`) VALUES ('11', '3', 'Tussents vine', '4.00', '1');
INSERT INTO `test`.`dish` (`id`, `restaraunt_id`, `name`, `price`, `active`) VALUES ('21', '15', 'ryncol', '5.55', '1');
INSERT INTO `test`.`dish` (`id`, `restaraunt_id`, `name`, `price`, `active`) VALUES ('23', '15', 'Toasted chicken leg', '1.45', '1');

