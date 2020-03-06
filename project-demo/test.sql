/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 80017
 Source Host           : localhost:3306
 Source Schema         : test

 Target Server Type    : MySQL
 Target Server Version : 80017
 File Encoding         : 65001

 Date: 26/12/2019 15:00:07
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_no` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订单号',
  `order_time` datetime(0) NOT NULL COMMENT '下单时间',
  `total_money` decimal(16, 2) NOT NULL COMMENT '订单金额',
  `point` int(11) NOT NULL COMMENT '订单获得积分',
  `user_fk` int(11) NULL DEFAULT NULL COMMENT '用户id',
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order
-- ----------------------------
INSERT INTO `order` VALUES (1, 'order20191216183900', '2019-12-16 18:40:02', 85.00, 85, 1, '2019-12-16 18:40:29', '2019-12-16 18:40:32');
INSERT INTO `order` VALUES (2, 'order20191216184152', '2019-12-16 18:41:19', 186.00, 186, 2, '2019-12-16 18:41:31', '2019-12-16 18:41:33');

-- ----------------------------
-- Table structure for order_goods
-- ----------------------------
DROP TABLE IF EXISTS `order_goods`;
CREATE TABLE `order_goods`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_fk` int(11) NOT NULL COMMENT '订单id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品名称',
  `total_money` decimal(16, 2) NOT NULL COMMENT '商品金额',
  `number` int(11) NULL DEFAULT NULL COMMENT '个数',
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order_goods
-- ----------------------------
INSERT INTO `order_goods` VALUES (1, 1, '华为mate9', 3500.00, 1, '2019-12-16 18:42:23', '2019-12-16 18:42:25');
INSERT INTO `order_goods` VALUES (2, 1, '玻璃杯', 70.00, 2, '2019-12-16 18:42:53', '2019-12-16 18:42:55');
INSERT INTO `order_goods` VALUES (3, 2, '华为运动手表', 1050.00, 1, '2019-12-16 18:43:43', '2019-12-16 18:43:45');
INSERT INTO `order_goods` VALUES (4, 2, '口罩', 45.00, 3, '2019-12-16 18:44:20', '2019-12-16 18:44:22');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(15) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电弧',
  `sex` int(1) NULL DEFAULT NULL COMMENT '性别，1-男，2-女',
  `age` int(3) NULL DEFAULT NULL COMMENT '年龄',
  `nick_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '住址',
  `status` int(1) NULL DEFAULT 0 COMMENT '状态，0-正常，1-不正常',
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'songxianzhuo', '11111111', '18310556635', 1, 29, '宋献卓', '北京市朝阳区十八里店乡吕家营', 0, '2019-12-16 18:17:25', '2019-12-16 18:17:29');
INSERT INTO `user` VALUES (3, 'sunjiajia', '22222222', '17600766583', 2, 27, '孙佳佳', '北京市东城区十里堡', 0, '2019-12-16 18:18:49', '2019-12-16 18:18:51');

SET FOREIGN_KEY_CHECKS = 1;
