/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50722 (5.7.22-log)
 Source Host           : localhost:3306
 Source Schema         : review

 Target Server Type    : MySQL
 Target Server Version : 50722 (5.7.22-log)
 File Encoding         : 65001

 Date: 17/05/2023 11:10:09
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for allocate
-- ----------------------------
DROP TABLE IF EXISTS `allocate`;
CREATE TABLE `allocate`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `allocate_user_id` int(11) NULL DEFAULT NULL,
  `group_id` int(11) NULL DEFAULT NULL,
  `allocate_time` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `expert_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of allocate
-- ----------------------------
INSERT INTO `allocate` VALUES (1, 1, 2, '2023-04-02 11:14:33.429', 4);
INSERT INTO `allocate` VALUES (2, 1, 3, '2023-04-02 14:07:04.846', 4);
INSERT INTO `allocate` VALUES (3, 1, 2, '2023-04-02 11:14:33.429', 4);

-- ----------------------------
-- Table structure for check_review_status
-- ----------------------------
DROP TABLE IF EXISTS `check_review_status`;
CREATE TABLE `check_review_status`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `review` int(11) NULL DEFAULT NULL COMMENT '评审任务id',
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `status` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否通过',
  `repeats` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 72 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of check_review_status
-- ----------------------------
INSERT INTO `check_review_status` VALUES (70, 55, '18891791102', '1', '同意');
INSERT INTO `check_review_status` VALUES (71, 55, '15991252344', '0', NULL);

-- ----------------------------
-- Table structure for entity_field
-- ----------------------------
DROP TABLE IF EXISTS `entity_field`;
CREATE TABLE `entity_field`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `field_id` int(11) NOT NULL,
  `entity_id` int(11) NULL DEFAULT NULL,
  `entity_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of entity_field
-- ----------------------------
INSERT INTO `entity_field` VALUES (1, 1, 5, 'PROJECT');
INSERT INTO `entity_field` VALUES (2, 1, 2, 'EXPERT');
INSERT INTO `entity_field` VALUES (3, 2, 6, 'PROJECT');
INSERT INTO `entity_field` VALUES (4, 1, 2, 'EXPERT');
INSERT INTO `entity_field` VALUES (5, 2, 2, 'EXPERT');
INSERT INTO `entity_field` VALUES (6, 1, 2, 'EXPERT');
INSERT INTO `entity_field` VALUES (7, 2, 2, 'EXPERT');
INSERT INTO `entity_field` VALUES (8, 2, 4, 'EXPERT');
INSERT INTO `entity_field` VALUES (9, 1, 2, 'EXPERT');
INSERT INTO `entity_field` VALUES (10, 3, 7, 'PROJECT');

-- ----------------------------
-- Table structure for expert_info
-- ----------------------------
DROP TABLE IF EXISTS `expert_info`;
CREATE TABLE `expert_info`  (
  `id` int(100) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `work_number` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL COMMENT '工号',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `expert_level` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '职级',
  `technology_level` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '技术等级',
  `field_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '擅长领域',
  `phone` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '手机号',
  `birthday` date NULL DEFAULT NULL COMMENT '出生日期',
  `age` int(11) NULL DEFAULT NULL COMMENT '年龄',
  `integral` int(11) NULL DEFAULT NULL COMMENT '参与评审积分',
  `refuse_count` int(11) NULL DEFAULT NULL COMMENT '拒绝评审次数',
  `expert_status` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '专家状态:正常,封禁',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `work_number_index`(`work_number`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 77 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of expert_info
-- ----------------------------
INSERT INTO `expert_info` VALUES (73, '000315', '张旺', '科员', '初级工程师', '财务', '15942357918', '1983-11-01', 68, 0, 0, '正常');
INSERT INTO `expert_info` VALUES (74, '000318', '张恒', '科员', '初级工程师', '工程1', '13659196637', '1968-11-02', 53, 0, 0, '正常');
INSERT INTO `expert_info` VALUES (75, '000329', '李珏', '正处', '高级工程师', '工程', '18891791102', '1983-11-03', 0, 14, 0, '正常');
INSERT INTO `expert_info` VALUES (76, '000378', '赵胜', '副处', '中级工程师', '工程', '15991252344', '1973-11-04', 0, 0, 0, '正常');

-- ----------------------------
-- Table structure for field
-- ----------------------------
DROP TABLE IF EXISTS `field`;
CREATE TABLE `field`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `field_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_user_id` int(11) NULL DEFAULT NULL,
  `createUserName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `last_update_time` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of field
-- ----------------------------
INSERT INTO `field` VALUES (1, '软件', 1, NULL, '2023-04-02 08:58:17.575', NULL);
INSERT INTO `field` VALUES (2, '工业', 3, NULL, '2023-04-02 10:37:05.401', NULL);
INSERT INTO `field` VALUES (3, '农业', 1, NULL, '2023-04-02 11:11:37.742', '2023-04-02 11:11:37.742');

-- ----------------------------
-- Table structure for project
-- ----------------------------
DROP TABLE IF EXISTS `project`;
CREATE TABLE `project`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `file_save_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `last_update_time` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `group_id` int(11) NULL DEFAULT NULL,
  `review_count` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_user_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of project
-- ----------------------------
INSERT INTO `project` VALUES (5, 'project', '测试用', 'REVIEW_DONE', 'D:\\prsUploadFiles\\新建 Microsoft Word 文档.docx', '2023-04-02 08:58:17.574', '2023-04-02 10:17:43.726', 0, NULL, 1);
INSERT INTO `project` VALUES (6, 'projec2', '测试12', 'REVIEW_DONE', 'D:\\prsUploadFiles\\新建 Microsoft Word 文档.docx', '2023-04-02 10:37:05.388', '2023-04-02 13:50:28.116', 2, NULL, 3);
INSERT INTO `project` VALUES (7, 'project', '项目摘要', 'WAIT_REVIEW', 'D:\\prsUploadFiles\\新建 Microsoft Word 文档.docx', '2023-04-02 13:17:37.227', '2023-04-02 22:07:04', 3, NULL, 3);

-- ----------------------------
-- Table structure for project_group
-- ----------------------------
DROP TABLE IF EXISTS `project_group`;
CREATE TABLE `project_group`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `group_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `last_update_time` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_user_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of project_group
-- ----------------------------
INSERT INTO `project_group` VALUES (2, '工业', '2023-04-02 10:20:11.15', '2023-04-02 11:14:33.433', 'ALLOCATE_DONE', 1);
INSERT INTO `project_group` VALUES (3, '软件', '2023-04-02 11:12:08.854', '2023-04-02 14:07:04.851', 'ALLOCATE_DONE', 1);
INSERT INTO `project_group` VALUES (4, '测试', '2023-04-12 07:18:59.683', '2023-04-12 07:18:59.683', 'WAIT_ADD_PROJECT', 1);

-- ----------------------------
-- Table structure for repeat_message
-- ----------------------------
DROP TABLE IF EXISTS `repeat_message`;
CREATE TABLE `repeat_message`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `time` datetime NULL DEFAULT NULL COMMENT '发送短信时间',
  `repeats` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '回复消息内容',
  `review` int(11) NULL DEFAULT NULL COMMENT '评审任务id',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `phone_index`(`phone`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of repeat_message
-- ----------------------------
INSERT INTO `repeat_message` VALUES (13, '15991252344', '2023-05-17 11:00:14', NULL, 55);

-- ----------------------------
-- Table structure for review
-- ----------------------------
DROP TABLE IF EXISTS `review`;
CREATE TABLE `review`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `applicant_id` int(11) NULL DEFAULT NULL,
  `project_id` int(11) NULL DEFAULT NULL,
  `expert_id` int(11) NULL DEFAULT NULL,
  `opinion` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `grade` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `review_time` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of review
-- ----------------------------
INSERT INTO `review` VALUES (1, 1, 5, 2, '不错', '50', '2023-04-02 10:17:43.712');
INSERT INTO `review` VALUES (2, 3, 6, 2, '测试', '60', '2023-04-02 13:48:45.099');
INSERT INTO `review` VALUES (3, 3, 6, 4, 'ceshi ', '70', '2023-04-02 13:50:28.108');

-- ----------------------------
-- Table structure for review_management
-- ----------------------------
DROP TABLE IF EXISTS `review_management`;
CREATE TABLE `review_management`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `review_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '评审任务名',
  `review_remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '评审内容概要',
  `review_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '评审发起人',
  `review_date` datetime NULL DEFAULT NULL COMMENT '评审计划日期',
  `review_start_date` datetime NULL DEFAULT NULL COMMENT '评审开始时间',
  `review_end_date` datetime NULL DEFAULT NULL COMMENT '评审结束时间',
  `review_field` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '评审所属专业领域',
  `review_experts` int(11) NULL DEFAULT NULL COMMENT '评审所需专家数量',
  `review_status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '评审状态',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `review_name_index`(`review_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 60 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of review_management
-- ----------------------------
INSERT INTO `review_management` VALUES (55, '长庆油田评估', '长庆油田评估1', '刘明', '2023-04-17 09:00:00', '2023-04-17 09:00:00', '2023-04-17 11:00:00', '工程', 2, '通知中');
INSERT INTO `review_management` VALUES (56, '大庆油田评估', '长庆油田评估2', '刘明', '2023-04-17 09:00:00', '2023-04-17 09:00:00', '2023-04-17 11:00:00', '工程', 2, '待评审');
INSERT INTO `review_management` VALUES (57, '西藏油田勘测', '长庆油田评估3', '刘明', '2023-04-15 09:00:00', '2023-04-17 09:00:00', '2023-04-17 11:00:00', '工程', 2, '待评审');
INSERT INTO `review_management` VALUES (58, '长庆油田勘测', '长庆油田评估4', '刘明', '2023-04-17 09:00:00', '2023-04-17 09:00:00', '2023-04-17 11:00:00', '工程', 2, '待评审');
INSERT INTO `review_management` VALUES (59, '长庆油田开发', '长庆油田评估5', '刘明', '2023-04-17 09:00:00', '2023-04-17 09:00:00', '2023-04-17 11:00:00', '工程', 2, '待评审');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sex` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `mail` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `role_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'admin', 'JdVa0oOqQAr0ZMdtcTwHrQ==', '男', NULL, NULL, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
