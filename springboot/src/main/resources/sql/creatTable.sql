create or replace database test;
use test;
create or replace table `testuser` (
  `user_name` varchar(100) NOT NULL,
  `password` varchar(100) DEFAULT NULL,
  `role` int NOT NULL,
  PRIMARY KEY (`user_name`),
  UNIQUE KEY `testuser_un` (`user_name`),
  KEY `TestUser_user_name_IDX` (`user_name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
insert into testuser values('wnlhdx','w1995520',1);
create or replace TABLE `plan` (
  `plan_name` varchar(100) NOT NULL,
  `time` varchar(100) NOT NULL,
  `detail` varchar(100) NOT NULL,
  `backup` varchar(100) DEFAULT NULL,
  `day_of_week` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`plan_name`),
  UNIQUE KEY `plan_un` (`plan_name`),
  UNIQUE KEY `time_un` (`time`),
  UNIQUE KEY `weekday_un` (`day_of_week`),
  KEY `plan_time_IDX` (`time`,`day_of_week`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;