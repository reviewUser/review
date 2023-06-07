/*
 Navicat Premium Data Transfer

 Source Server         : review
 Source Server Type    : MySQL
 Source Server Version : 50722
 Source Host           : 121.41.170.49:3306
 Source Schema         : review

 Target Server Type    : MySQL
 Target Server Version : 50722
 File Encoding         : 65001

 Date: 07/06/2023 17:49:32
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
) ENGINE = InnoDB AUTO_INCREMENT = 98 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of check_review_status
-- ----------------------------
INSERT INTO `check_review_status` VALUES (82, 85, '15991252344', '1', '同意');
INSERT INTO `check_review_status` VALUES (83, 85, '18891791102', '1', '同意');
INSERT INTO `check_review_status` VALUES (84, 86, '15991252344', '1', '拒绝');
INSERT INTO `check_review_status` VALUES (85, 86, '18891791102', '1', '拒绝');
INSERT INTO `check_review_status` VALUES (86, 91, '15991252344', '0', '暂未回复');
INSERT INTO `check_review_status` VALUES (87, 91, '18891791102', '0', '暂未回复');
INSERT INTO `check_review_status` VALUES (88, 90, '15991252344', '1', '拒绝');
INSERT INTO `check_review_status` VALUES (89, 90, '18891791102', '1', '同意');
INSERT INTO `check_review_status` VALUES (90, 92, '13659196637', '1', '拒绝');
INSERT INTO `check_review_status` VALUES (91, 92, '18591981653', '1', '拒绝');
INSERT INTO `check_review_status` VALUES (92, 102, '13659196637', '1', '拒绝');
INSERT INTO `check_review_status` VALUES (93, 102, '15991252344', '1', '同意');
INSERT INTO `check_review_status` VALUES (94, 100, '15319783096', '1', '同意');
INSERT INTO `check_review_status` VALUES (95, 100, '13572081862', '1', '拒绝');
INSERT INTO `check_review_status` VALUES (96, 101, '15319783096', '1', '拒绝');
INSERT INTO `check_review_status` VALUES (97, 101, '13572081862', '1', '拒绝');

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
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所在区域',
  `work_unit` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '工作单位',
  `phone` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '手机号',
  `birthday` date NULL DEFAULT NULL COMMENT '出生日期',
  `age` int(11) NULL DEFAULT NULL COMMENT '年龄',
  `integral` int(11) NULL DEFAULT NULL COMMENT '参与评审积分',
  `refuse_count` int(11) NULL DEFAULT NULL COMMENT '拒绝评审次数',
  `expert_status` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '专家状态:正常,封禁',
  `un_meeting` int(11) NULL DEFAULT NULL COMMENT '未参会次数',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `work_number_index`(`work_number`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 136 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of expert_info
-- ----------------------------
INSERT INTO `expert_info` VALUES (130, '000315', '张旺', '科员', '初级工程师', '工程', '研究所', '华东', '15991252344', '1965-06-06', 57, 1, 0, '正常', 0);
INSERT INTO `expert_info` VALUES (131, '000318', '张恒', '科员', '初级工程师', '工程', '研究所', '华东', '13659196637', '1968-11-02', 54, 0, 2, '正常', 0);
INSERT INTO `expert_info` VALUES (132, '000329', '李珏', '正处', '高级工程师', '工程', '研究所', '华东', '18591981653', '1983-11-03', 39, 0, 1, '正常', 0);
INSERT INTO `expert_info` VALUES (133, '000378', '赵胜', '副处', '中级工程师', '财务', '研究所', '华东', '15319783096', '1973-11-04', 49, 1, 1, '正常', 0);
INSERT INTO `expert_info` VALUES (134, '000415', '刘飞', '正科', '中级工程师', '地质', '研究所', '华东', '17391986497', '1978-12-04', 44, 0, 0, '正常', 0);
INSERT INTO `expert_info` VALUES (135, '000484', '胡静', '正科', '高级工程师', '财务', '研究所', '华东', '13572081862', '1988-07-02', 34, 0, 2, '正常', 0);

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
-- Table structure for instrument_information
-- ----------------------------
DROP TABLE IF EXISTS `instrument_information`;
CREATE TABLE `instrument_information`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `commission` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '委托单位',
  `equipment` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '设备名称',
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '型号规格',
  `number` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '编号',
  `creator` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '制造商',
  `accuracy_level` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '准确度等级',
  `range_measure` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '测量范围',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `number_index`(`number`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of instrument_information
-- ----------------------------
INSERT INTO `instrument_information` VALUES (8, '1A', '数字表', 'SR91', '123', 'YUDIAN', '0.5', '(0～400)');

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
) ENGINE = InnoDB AUTO_INCREMENT = 27 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of repeat_message
-- ----------------------------

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
  `fund_source` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `source_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `review_name_index`(`review_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 106 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of review_management
-- ----------------------------
INSERT INTO `review_management` VALUES (100, '长庆油田评估', '长庆油田评估1', '刘明', '2023-04-17 00:00:00', '2023-04-17 09:00:00', '2023-04-17 11:00:00', '财务', 2, '部分通知完成', '研究所', '西安', '华东');
INSERT INTO `review_management` VALUES (101, '大庆油田评估', '长庆油田评估2', '刘明', '2023-04-17 00:00:00', '2023-04-17 09:00:00', '2023-04-17 11:00:00', '财务', 2, '通知失败', '研究所', '西安', '华东');
INSERT INTO `review_management` VALUES (102, '西藏油田勘测', '长庆油田评估3', '刘明', '2023-04-15 00:00:00', '2023-04-17 09:00:00', '2023-04-17 11:00:00', '工程', 2, '部分通知完成', '研究所', '西安', '华东');
INSERT INTO `review_management` VALUES (103, '长庆油田勘测', '长庆油田评估4', '刘明', '2023-04-17 00:00:00', '2023-04-17 09:00:00', '2023-04-17 11:00:00', '地质', 1, '通知中', '研究所', '西安', '华东');
INSERT INTO `review_management` VALUES (104, '长庆油田开发', '长庆油田评估5', '刘明', '2023-04-17 00:00:00', '2023-04-17 09:00:00', '2023-04-17 11:00:00', '工程', 2, '待评审', '研究所', '西安', '华东');
INSERT INTO `review_management` VALUES (105, '长庆油田检测', '长庆油田评估6', '刘明', '2023-04-18 00:00:00', '2023-04-17 10:00:00', '2023-04-17 12:00:00', '工程', 2, '待评审', '研究所', '西安', '华东');

-- ----------------------------
-- Table structure for standard_information
-- ----------------------------
DROP TABLE IF EXISTS `standard_information`;
CREATE TABLE `standard_information`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名称',
  `use_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '型号规格',
  `use_number` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '编号',
  `use_level` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '不确定度',
  `end_time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '有效期至',
  `cert` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '证书号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of standard_information
-- ----------------------------
INSERT INTO `standard_information` VALUES (3, '校准器', 'jy941', '567', '0.2', '2023.05.15', 'YJKL123');

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

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `sex` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `number` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `number_index`(`number`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 28 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (25, '张三', '女', '55');
INSERT INTO `t_user` VALUES (26, '李四', '男', '66');
INSERT INTO `t_user` VALUES (27, '赵柳', '男', '77');

SET FOREIGN_KEY_CHECKS = 1;
