create or replace database test;
use test;
create or replace table `testuser` (
  `user_name` varchar(100) NOT NULL,
  `password` varchar(100) DEFAULT NULL,
  `role` int(11) NOT NULL,
  PRIMARY KEY (`user_name`),
  UNIQUE KEY `testuser_un` (`user_name`),
  KEY `TestUser_user_name_IDX` (`user_name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;
insert into testuser values('wnlhdx','w1995520',1);
create or replace table  `plan` (
  `plan_name` varchar(100) NOT NULL,
  `start_time` varchar(100) NOT NULL,
  `end_time` varchar(100) NOT NULL,
  `day_of_week` varchar(100) DEFAULT NULL,
  `start_time_night` varchar(100) NOT NULL,
  `end_time_night` varchar(100) NOT NULL,
  `function_for` varchar(100) NOT NULL,
  `read_book` varchar(100) NOT NULL,
  `read_book_pages` varchar(100) NOT NULL,
  `rest_time_app` varchar(100) NOT NULL,
  PRIMARY KEY (`plan_name`),
  KEY `plan_FK_1` (`read_book`),
  CONSTRAINT `plan_FK` FOREIGN KEY (`plan_name`) REFERENCES `project` (`plan_name`),
  CONSTRAINT `plan_FK_1` FOREIGN KEY (`read_book`) REFERENCES `book_history` (`book_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;
create or replace table  `project` (
  `plan_name` varchar(100) NOT NULL,
  `project` varchar(100) NOT NULL,
  `project_this_week` varchar(100) NOT NULL,
  `project_this_month` varchar(100) DEFAULT NULL,
  `project_this_year` varchar(100) NOT NULL,
  PRIMARY KEY (`plan_name`),
  KEY `project_FK` (`project`),
  CONSTRAINT `project_FK` FOREIGN KEY (`project`) REFERENCES `project_history` (`project`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;
create or replace table  `project_history` (
  `project` varchar(100) NOT NULL,
  `project_time_year` varchar(100) NOT NULL,
  `project_time_month` varchar(100) NOT NULL,
  `project_time_week` varchar(100) NOT NULL,
  `project_finish_percent` varchar(100) NOT NULL,
  `project_finish_details` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`project`),
  KEY `project_history_project_time_year_IDX` (`project_time_year`,`project_time_month`,`project_time_week`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;
create or replace table  `book_history` (
  `book_name` varchar(100) NOT NULL,
  `book_time_year` varchar(100) NOT NULL,
  `book_time_month` varchar(100) NOT NULL,
  `book_time_week` varchar(100) NOT NULL,
  `book_finish_percent` varchar(100) NOT NULL,
  `book_finish_details` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`book_name`),
  KEY `book_history_book_time_year_IDX` (`book_time_year`,`book_time_month`,`book_time_week`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;