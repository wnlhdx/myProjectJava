create database test;
use test;
CREATE TABLE `testuser` (
  `user_name` varchar(100) NOT NULL,
  `password` varchar(100) DEFAULT NULL,
  `role` int NOT NULL,
  PRIMARY KEY (`user_name`),
  UNIQUE KEY `testuser_un` (`user_name`),
  KEY `TestUser_user_name_IDX` (`user_name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
insert into testuser values('wnlhdx','w1995520',1);
