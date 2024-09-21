/*
 Navicat Premium Dump SQL

 Source Server         : test
 Source Server Type    : SQLite
 Source Server Version : 3045000 (3.45.0)
 Source Schema         : main

 Target Server Type    : SQLite
 Target Server Version : 3045000 (3.45.0)
 File Encoding         : 65001

 Date: 20/09/2024 05:44:06
*/


-- ----------------------------
-- Table structure for files
-- ----------------------------
DROP TABLE IF EXISTS "files";
CREATE TABLE files (
  "file_name" text,
  "size" integer,
  "path" text,
  "system" text,
  "detail" text,
  "update_time" text
);

-- ----------------------------
-- Records of files
-- ----------------------------


/*
 Navicat Premium Dump SQL

 Source Server         : test
 Source Server Type    : SQLite
 Source Server Version : 3045000 (3.45.0)
 Source Schema         : main

 Target Server Type    : SQLite
 Target Server Version : 3045000 (3.45.0)
 File Encoding         : 65001

 Date: 20/09/2024 05:44:26
*/


-- ----------------------------
-- Table structure for plans
-- ----------------------------
DROP TABLE IF EXISTS "plans";
CREATE TABLE plans (
  "plan_name" text,
  "time_start" integer,
  "time_end" integer,
  "plan_details" text,
  "project_name" text,
  "project_details" text,
  "night_time_start" integer,
  "night_time_end" integer,
  "project_finish_percent" text,
  "day_of_week" integer,
  "book_name" text,
  "book_content" text,
  "major_in" text,
  "project_month" text,
  "project_year" text,
  "relax_item" text,
  "relax_item_foregin" text,
  "type_of_learn" text,
  "type_detail" text,
  "standard_learn" text,
  "update_time" text
);

-- ----------------------------
-- Records of plans
-- ----------------------------



/*
 Navicat Premium Dump SQL

 Source Server         : test
 Source Server Type    : SQLite
 Source Server Version : 3045000 (3.45.0)
 Source Schema         : main

 Target Server Type    : SQLite
 Target Server Version : 3045000 (3.45.0)
 File Encoding         : 65001

 Date: 20/09/2024 05:44:46
*/


-- ----------------------------
-- Table structure for rankdata
-- ----------------------------
DROP TABLE IF EXISTS "rankdata";
CREATE TABLE rankdata (
  "data_name" text,
  "data_type" text,
  "data_detail" text,
  "data_resource_webste" text,
  "data_introduction" text,
  "data_rank" integer,
  "update_time" text
);

-- ----------------------------
-- Records of rankdata
-- ----------------------------



/*
 Navicat Premium Dump SQL

 Source Server         : test
 Source Server Type    : SQLite
 Source Server Version : 3045000 (3.45.0)
 Source Schema         : main

 Target Server Type    : SQLite
 Target Server Version : 3045000 (3.45.0)
 File Encoding         : 65001

 Date: 20/09/2024 05:44:55
*/


-- ----------------------------
-- Table structure for softs
-- ----------------------------
DROP TABLE IF EXISTS "softs";
CREATE TABLE softs (
  "soft_name" text,
  "download_type" text,
  "soft_path" text,
  "download_path" text,
  "detail" text,
  "update_time" text
);

-- ----------------------------
-- Records of softs
-- ----------------------------

