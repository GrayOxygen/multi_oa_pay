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
-- Dumping data for table `official_account`
--

LOCK TABLES `official_account` WRITE;
/*!40000 ALTER TABLE `official_account` DISABLE KEYS */;
INSERT INTO `official_account` VALUES ('0','地产服务商公众号','wx737ad133237892d7','af7b6b5474fc4cba69682344500c1b98','1483579272','Trnlsoft091288890334922019231209','FWS','2016-03-23 14:55:17','2016-03-23 14:55:17',0,'xx','123'),('1','英皇公众号','wxcd907509d4344b06','f833fcc12db9d7887676d263358cf703','1483848872','M1FVtD1K4FSRoCXwSKwVj9vV2mPOMTkupIKv1ZFfTaJ','PTS','2017-06-19 18:19:03','2017-06-21 10:19:41',0,'xxxxxxx','trnl'),('2','新环物业','wx40d365f3b7ba0644','c5d465ab045b979945c604cf0d28381d','1483849762','c5d465ab045b979945c604cf0d28381d','PTS','2016-03-23 14:55:17','2016-03-23 14:55:17',0,'xx','trnl'),('3','博汇物业','wx10a590dea6c4eae5','2ae17069f3ef51410b846d298fa4cad3','1483852252','TRNLtrnlsoft88trnltrnlsoft88tgD8','PTS','2016-03-23 14:55:17','2016-03-23 14:55:17',0,'dddd1','trnl'),('4','新基物业','wxf05189f68ef5d4c7','d732b844180515fc292b9764811b3540','1483848192','c5d465ab045b979945c604cf0d28381d','PTS','2016-03-23 14:55:17','2016-03-23 14:55:17',0,NULL,'trnl');
/*!40000 ALTER TABLE `official_account` ENABLE KEYS */;
UNLOCK TABLES;

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
  `unionid` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_appId` (`appId`),
  KEY `idx_mchId` (`mchId`),
  KEY `idx_subAppId` (`subAppId`),
  KEY `idx_subMchId` (`subMchId`),
  KEY `idx_ctime` (`ctime`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pay_order`
--

LOCK TABLES `pay_order` WRITE;
/*!40000 ALTER TABLE `pay_order` DISABLE KEYS */;
/*!40000 ALTER TABLE `pay_order` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-07-20 17:12:38
