-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: multi_oa_pay
-- ------------------------------------------------------
-- Server version	5.7.17-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `official_account`
--

DROP TABLE IF EXISTS `official_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `official_account` (
  `id` varchar(32) NOT NULL,
  `name` varchar(15) NOT NULL,
  `appId` varchar(18) NOT NULL,
  `secret` varchar(32) NOT NULL,
  `mchId` varchar(10) NOT NULL,
  `key` varchar(43) NOT NULL,
  `type` enum('PTS','FWS') NOT NULL,
  `ctime` datetime DEFAULT NULL,
  `mtime` datetime DEFAULT NULL,
  `deleted` tinyint(1) DEFAULT '0',
  `templateId` varchar(64) DEFAULT NULL,
  `token` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_appId` (`appId`),
  KEY `idx_mchId` (`mchId`),
  KEY `idx_name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `pay_order`
--

DROP TABLE IF EXISTS `pay_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pay_order` (
  `id` varchar(64) NOT NULL,
  `appId` varchar(32) NOT NULL COMMENT '公众账号ID',
  `mchId` varchar(32) NOT NULL COMMENT '商户号',
  `subAppId` varchar(32) DEFAULT NULL COMMENT '子商户公众账号ID',
  `subMchId` varchar(32) DEFAULT NULL COMMENT '子商户号',
  `deviceInfo` varchar(32) DEFAULT NULL COMMENT '微信支付分配的终端设备号',
  `nonceStr` varchar(64) NOT NULL COMMENT '随机字符串',
  `body` varchar(32) NOT NULL COMMENT '商品或支付单简要描述',
  `detail` text COMMENT '商品名称明细列表',
  `attach` varchar(127) DEFAULT NULL COMMENT '附加数据',
  `feeType` enum('CNY') DEFAULT NULL COMMENT '货币种类',
  `totalFee` int(11) NOT NULL COMMENT '总金额',
  `spBillCreateIp` varchar(16) NOT NULL COMMENT 'APP和网页支付提交用户端ip，Native支付填调用微信支付API的机器IP',
  `timeStart` varchar(14) DEFAULT NULL COMMENT '订单生成时间，格式为yyyyMMddHHmmss',
  `timeExpire` varchar(14) DEFAULT NULL COMMENT '订单失效时间，格式为yyyyMMddHHmmss',
  `goodsTag` varchar(32) DEFAULT NULL COMMENT '商品标记',
  `notifyUrl` varchar(255) DEFAULT NULL COMMENT '通知地址',
  `tradeType` enum('JSAPI','NATIVE','APP') NOT NULL COMMENT '调用接口提交的交易类型，取值如下：JSAPI，NATIVE，APP，MICROPAY',
  `productId` varchar(32) NOT NULL COMMENT '商品ID',
  `openid` varchar(128) DEFAULT NULL COMMENT '用户在商户appid下的唯一标识',
  `subOpenid` varchar(128) DEFAULT NULL COMMENT '用户子标识',
  `prepayId` varchar(64) DEFAULT NULL COMMENT '微信生成的预支付回话标识，用于后续接口调用中使用，该值有效期为2小时',
  `codeUrl` varchar(64) DEFAULT NULL COMMENT '扫码支付的二维码链接',
  `status` enum('CREATED','COMMITTED','SUCCESS') NOT NULL COMMENT '状态',
  `tradeState` enum('SUCCESS','REFUND','NOTPAY','CLOSED','REVOKED','USERPAYING','PAYERROR') DEFAULT NULL COMMENT '交易状态',
  `subscribed` tinyint(4) DEFAULT NULL COMMENT '用户是否关注公众账号，Y-关注，N-未关注，仅在公众账号类型支付有效',
  `subSubscribed` tinyint(4) DEFAULT NULL COMMENT '用户是否关注子公众账号，Y-关注，N-未关注，仅在公众账号类型支付有效',
  `bankType` varchar(16) DEFAULT NULL COMMENT '付款银行',
  `cashFee` int(11) DEFAULT NULL COMMENT '现金支付金额',
  `cashFeeType` enum('CNY') DEFAULT NULL COMMENT '现金支付货币类型',
  `couponFee` int(11) DEFAULT NULL COMMENT '代金券或立减优惠金额',
  `couponCount` int(11) DEFAULT NULL COMMENT '代金券或立减优惠使用数量',
  `transactionId` varchar(32) DEFAULT NULL COMMENT '微信支付订单号',
  `timeEnd` varchar(14) DEFAULT NULL COMMENT '支付完成时间',
  `ctime` datetime NOT NULL,
  `mtime` datetime DEFAULT NULL,
  `shopId` varchar(32) DEFAULT NULL COMMENT '订单发起人所在门店',
  `companyId` varchar(32) DEFAULT NULL COMMENT '订单发起人所在商户，冗余便于sql查询',
  `cashierId` varchar(32) DEFAULT NULL COMMENT '收银员id',
  PRIMARY KEY (`id`),
  KEY `idx_appId` (`appId`),
  KEY `idx_mchId` (`mchId`),
  KEY `idx_subAppId` (`subAppId`),
  KEY `idx_subMchId` (`subMchId`),
  KEY `idx_ctime` (`ctime`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-07-05 12:23:49
