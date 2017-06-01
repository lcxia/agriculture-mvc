-- MySQL dump 10.13  Distrib 5.5.40, for Win32 (x86)
--
-- Host: localhost    Database: agriculture
-- ------------------------------------------------------
-- Server version	5.5.40

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
-- Table structure for table `alipay_history`
--

DROP TABLE IF EXISTS `alipay_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `alipay_history` (
  `out_trade_no` varchar(45) NOT NULL,
  `subject` varchar(450) DEFAULT NULL,
  `price` varchar(45) DEFAULT NULL,
  `body` varchar(450) DEFAULT NULL,
  `show_url` varchar(450) DEFAULT NULL,
  `receive_name` varchar(45) DEFAULT NULL,
  `receive_address` varchar(450) DEFAULT NULL,
  `receive_zip` varchar(45) DEFAULT NULL,
  `receive_phone` varchar(45) DEFAULT NULL,
  `receive_mobile` varchar(45) DEFAULT NULL,
  `guest_id` varchar(45) DEFAULT NULL,
  `update_time` datetime NOT NULL,
  `update_user` varchar(45) NOT NULL,
  PRIMARY KEY (`out_trade_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `alipay_history`
--

LOCK TABLES `alipay_history` WRITE;
/*!40000 ALTER TABLE `alipay_history` DISABLE KEYS */;
/*!40000 ALTER TABLE `alipay_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `brand`
--

DROP TABLE IF EXISTS `brand`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `brand` (
  `brand_id` varchar(45) NOT NULL,
  `supplier_id` varchar(45) DEFAULT NULL,
  `brand_name` varchar(45) DEFAULT NULL,
  `note` varchar(45) DEFAULT NULL,
  `update_time` datetime NOT NULL,
  `update_user` varchar(45) NOT NULL,
  PRIMARY KEY (`brand_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `brand`
--

LOCK TABLES `brand` WRITE;
/*!40000 ALTER TABLE `brand` DISABLE KEYS */;
INSERT INTO `brand` VALUES ('201500000000001','201500000000001','日思','','2015-06-08 12:15:49','管理员'),('201500000000002','201500000000002','津沽','','2015-06-08 12:16:06','管理员'),('201500000000003','201500000000003','黄庄洼','','2015-06-08 12:16:19','管理员'),('201500000000004','201500000000004','利民','','2015-06-08 12:15:13','管理员'),('201500000000005','201500000000005','品美','','2015-06-08 12:16:45','管理员'),('201500000000006','201500000000006','正达','','2015-06-08 12:17:01','管理员');
/*!40000 ALTER TABLE `brand` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cart`
--

DROP TABLE IF EXISTS `cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cart` (
  `cart_id` varchar(45) NOT NULL,
  `commodity_id` varchar(45) DEFAULT NULL,
  `guest_id` varchar(45) DEFAULT NULL,
  `count` varchar(45) DEFAULT NULL,
  `update_time` datetime NOT NULL,
  `update_user` varchar(45) NOT NULL,
  `status` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`cart_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart`
--

LOCK TABLES `cart` WRITE;
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `charge`
--

DROP TABLE IF EXISTS `charge`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `charge` (
  `charge_id` varchar(45) NOT NULL,
  `amount` varchar(45) DEFAULT NULL,
  `distributor_id` varchar(45) DEFAULT NULL,
  `charge_date` datetime DEFAULT NULL,
  `update_time` datetime NOT NULL,
  `update_user` varchar(45) NOT NULL,
  `status` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`charge_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `charge`
--

LOCK TABLES `charge` WRITE;
/*!40000 ALTER TABLE `charge` DISABLE KEYS */;
/*!40000 ALTER TABLE `charge` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `city`
--

DROP TABLE IF EXISTS `city`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `city` (
  `city_id` varchar(10) NOT NULL,
  `province_id` varchar(10) DEFAULT NULL,
  `city_name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`city_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `city`
--

LOCK TABLES `city` WRITE;
/*!40000 ALTER TABLE `city` DISABLE KEYS */;
INSERT INTO `city` VALUES ('0000000001','0000000001','北京市'),('0000000002','0000000002','上海市'),('0000000003','0000000003','天津市'),('0000000004','0000000004','重庆市'),('0000000005','0000000005','保定市');
/*!40000 ALTER TABLE `city` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `commodity`
--

DROP TABLE IF EXISTS `commodity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `commodity` (
  `commodity_id` varchar(45) NOT NULL,
  `type` varchar(45) DEFAULT NULL,
  `supplier_id` varchar(45) DEFAULT NULL,
  `brand_id` varchar(45) DEFAULT NULL,
  `commodity_name` varchar(45) DEFAULT NULL,
  `weight` varchar(45) DEFAULT NULL,
  `is_gift` varchar(45) DEFAULT NULL,
  `specifications` varchar(45) DEFAULT NULL,
  `unit` varchar(45) DEFAULT NULL,
  `benchmark_price` varchar(45) DEFAULT NULL,
  `guide_price` varchar(45) DEFAULT NULL,
  `retail_price` varchar(45) DEFAULT NULL,
  `competition_level` varchar(45) DEFAULT NULL,
  `note` varchar(45) DEFAULT NULL,
  `update_time` datetime NOT NULL,
  `update_user` varchar(45) NOT NULL,
  `picture_id` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`commodity_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `commodity`
--

LOCK TABLES `commodity` WRITE;
/*!40000 ALTER TABLE `commodity` DISABLE KEYS */;
/*!40000 ALTER TABLE `commodity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `distributor`
--

DROP TABLE IF EXISTS `distributor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `distributor` (
  `distributor_id` varchar(45) NOT NULL,
  `password` varchar(45) DEFAULT NULL,
  `distributor_name` varchar(45) DEFAULT NULL,
  `type` varchar(45) DEFAULT NULL,
  `level` varchar(45) DEFAULT NULL,
  `print_name` varchar(45) DEFAULT NULL,
  `province_id` varchar(45) DEFAULT NULL,
  `city_id` varchar(45) DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  `contacts` varchar(45) DEFAULT NULL,
  `fax` varchar(45) DEFAULT NULL,
  `telephone` varchar(45) DEFAULT NULL,
  `mobile` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `wechat` varchar(45) DEFAULT NULL,
  `qq` varchar(45) DEFAULT NULL,
  `alipay` varchar(45) DEFAULT NULL,
  `note` varchar(45) DEFAULT NULL,
  `update_time` varchar(45) NOT NULL,
  `update_user` varchar(45) NOT NULL,
  `price_increment` varchar(45) DEFAULT NULL,
  `amount` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`distributor_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `distributor`
--

LOCK TABLES `distributor` WRITE;
/*!40000 ALTER TABLE `distributor` DISABLE KEYS */;
/*!40000 ALTER TABLE `distributor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `distributor_price`
--

DROP TABLE IF EXISTS `distributor_price`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `distributor_price` (
  `distributor_price_id` varchar(45) NOT NULL,
  `distributor_id` varchar(45) DEFAULT NULL,
  `commodity_id` varchar(45) DEFAULT NULL,
  `retail_price` varchar(45) DEFAULT NULL,
  `update_time` datetime NOT NULL,
  `update_user` varchar(45) NOT NULL,
  PRIMARY KEY (`distributor_price_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `distributor_price`
--

LOCK TABLES `distributor_price` WRITE;
/*!40000 ALTER TABLE `distributor_price` DISABLE KEYS */;
/*!40000 ALTER TABLE `distributor_price` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `express`
--

DROP TABLE IF EXISTS `express`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `express` (
  `express_id` varchar(45) NOT NULL,
  `express_name` varchar(45) DEFAULT NULL,
  `contacts` varchar(45) DEFAULT NULL,
  `mobile` varchar(45) DEFAULT NULL,
  `fax` varchar(45) DEFAULT NULL,
  `telephone` varchar(45) DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  `note` varchar(45) DEFAULT NULL,
  `update_time` datetime NOT NULL,
  `update_user` varchar(45) NOT NULL,
  PRIMARY KEY (`express_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `express`
--

LOCK TABLES `express` WRITE;
/*!40000 ALTER TABLE `express` DISABLE KEYS */;
INSERT INTO `express` VALUES ('201500000000001','全峰快递','王经理','13516290392','','','天津市河北区开云大厦','启用','','2015-06-08 12:12:01','管理员'),('201500000000002','顺丰速递','魏磊','15510812575','','','天津市河北区开云大厦','启用','','2015-06-08 12:17:51','管理员');
/*!40000 ALTER TABLE `express` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `express_list`
--

DROP TABLE IF EXISTS `express_list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `express_list` (
  `express_list_id` decimal(45,0) NOT NULL,
  `express_id` varchar(45) DEFAULT NULL,
  `is_used` varchar(45) DEFAULT NULL,
  `order_id` varchar(45) DEFAULT NULL,
  `used_date` datetime DEFAULT NULL,
  `note` varchar(45) DEFAULT NULL,
  `storage` varchar(45) DEFAULT NULL,
  `update_time` datetime NOT NULL,
  `update_user` varchar(45) NOT NULL,
  PRIMARY KEY (`express_list_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `express_list`
--

LOCK TABLES `express_list` WRITE;
/*!40000 ALTER TABLE `express_list` DISABLE KEYS */;
/*!40000 ALTER TABLE `express_list` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `express_price`
--

DROP TABLE IF EXISTS `express_price`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `express_price` (
  `express_price_id` varchar(45) NOT NULL,
  `express_id` varchar(45) DEFAULT NULL,
  `province_id` varchar(45) DEFAULT NULL,
  `separate_weight` varchar(45) DEFAULT NULL,
  `first_heavy_price` varchar(45) DEFAULT NULL,
  `continued_heavy_price` varchar(45) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  `note` varchar(45) DEFAULT NULL,
  `update_time` datetime NOT NULL,
  `update_user` varchar(45) NOT NULL,
  PRIMARY KEY (`express_price_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `express_price`
--

LOCK TABLES `express_price` WRITE;
/*!40000 ALTER TABLE `express_price` DISABLE KEYS */;
/*!40000 ALTER TABLE `express_price` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `guest`
--

DROP TABLE IF EXISTS `guest`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `guest` (
  `guest_id` varchar(45) NOT NULL,
  `guest_name` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `gender` varchar(45) DEFAULT NULL,
  `address` varchar(450) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `qq` varchar(45) DEFAULT NULL,
  `mobile` varchar(45) DEFAULT NULL,
  `update_time` datetime NOT NULL,
  `update_user` varchar(45) NOT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `zip` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`guest_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `guest`
--

LOCK TABLES `guest` WRITE;
/*!40000 ALTER TABLE `guest` DISABLE KEYS */;
/*!40000 ALTER TABLE `guest` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `picture`
--

DROP TABLE IF EXISTS `picture`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `picture` (
  `picture_id` varchar(45) NOT NULL,
  `picture` longblob,
  PRIMARY KEY (`picture_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `picture`
--

LOCK TABLES `picture` WRITE;
/*!40000 ALTER TABLE `picture` DISABLE KEYS */;
/*!40000 ALTER TABLE `picture` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `place_order`
--

DROP TABLE IF EXISTS `place_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `place_order` (
  `place_order_id` varchar(45) NOT NULL,
  `storage` varchar(45) DEFAULT NULL,
  `transport_mode` varchar(45) DEFAULT NULL,
  `guest_from` varchar(45) DEFAULT NULL,
  `guest_company` varchar(45) DEFAULT NULL,
  `province_id` varchar(45) DEFAULT NULL,
  `city_id` varchar(45) DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  `contacts` varchar(45) DEFAULT NULL,
  `mobile` varchar(45) DEFAULT NULL,
  `zip` varchar(45) DEFAULT NULL,
  `telephone` varchar(45) DEFAULT NULL,
  `order_date` date DEFAULT NULL,
  `note` varchar(45) DEFAULT NULL,
  `amount` varchar(45) DEFAULT NULL,
  `update_time` datetime NOT NULL,
  `update_user` varchar(45) NOT NULL,
  `user_id` varchar(45) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`place_order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `place_order`
--

LOCK TABLES `place_order` WRITE;
/*!40000 ALTER TABLE `place_order` DISABLE KEYS */;
/*!40000 ALTER TABLE `place_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `place_order_detail`
--

DROP TABLE IF EXISTS `place_order_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `place_order_detail` (
  `place_order_detail_id` varchar(45) NOT NULL,
  `place_order_id` varchar(45) DEFAULT NULL,
  `commodity_id` varchar(45) DEFAULT NULL,
  `purchase_count` varchar(45) DEFAULT NULL,
  `note` varchar(45) DEFAULT NULL,
  `update_time` varchar(45) NOT NULL,
  `update_user` varchar(45) NOT NULL,
  PRIMARY KEY (`place_order_detail_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `place_order_detail`
--

LOCK TABLES `place_order_detail` WRITE;
/*!40000 ALTER TABLE `place_order_detail` DISABLE KEYS */;
/*!40000 ALTER TABLE `place_order_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `province`
--

DROP TABLE IF EXISTS `province`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `province` (
  `province_id` varchar(10) NOT NULL,
  `province_name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`province_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `province`
--

LOCK TABLES `province` WRITE;
/*!40000 ALTER TABLE `province` DISABLE KEYS */;
INSERT INTO `province` VALUES ('0000000001','北京市'),('0000000002','上海市'),('0000000003','天津市'),('0000000004','重庆市'),('0000000005','河北省'),('0000000006','山东省'),('0000000007','辽宁省'),('0000000008','黑龙江省'),('0000000009','吉林省'),('0000000010','甘肃省'),('0000000011','青海省'),('0000000012','河南省'),('0000000013','江苏省'),('0000000014','湖北省'),('0000000015','湖南省'),('0000000016','江西省'),('0000000017','浙江省'),('0000000018','广东省'),('0000000019','云南省'),('0000000020','福建省'),('0000000021','台湾省'),('0000000022','海南省'),('0000000023','山西省'),('0000000024','四川省'),('0000000025','陕西省'),('0000000026','贵州省'),('0000000027','安徽省'),('0000000028','广西壮族自治区'),('0000000029','内蒙古自治区'),('0000000030','西藏自治区'),('0000000031','新疆维吾尔自治区'),('0000000032','宁夏回族自治区'),('0000000033','澳门特别行政区'),('0000000034','香港特别行政区');
/*!40000 ALTER TABLE `province` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `special_ip`
--

DROP TABLE IF EXISTS `special_ip`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `special_ip` (
  `special_ip` varchar(45) NOT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`special_ip`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `special_ip`
--

LOCK TABLES `special_ip` WRITE;
/*!40000 ALTER TABLE `special_ip` DISABLE KEYS */;
/*!40000 ALTER TABLE `special_ip` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stock`
--

DROP TABLE IF EXISTS `stock`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stock` (
  `stock_id` varchar(45) NOT NULL,
  `commodity_id` varchar(45) DEFAULT NULL,
  `stock` varchar(45) DEFAULT NULL,
  `note` varchar(45) DEFAULT NULL,
  `update_time` datetime NOT NULL,
  `update_user` varchar(45) NOT NULL,
  PRIMARY KEY (`stock_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stock`
--

LOCK TABLES `stock` WRITE;
/*!40000 ALTER TABLE `stock` DISABLE KEYS */;
/*!40000 ALTER TABLE `stock` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `supplier`
--

DROP TABLE IF EXISTS `supplier`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `supplier` (
  `supplier_id` varchar(45) NOT NULL,
  `type` varchar(45) DEFAULT NULL,
  `level` varchar(45) DEFAULT NULL,
  `supplier_name` varchar(45) DEFAULT NULL,
  `province_id` varchar(10) DEFAULT NULL,
  `city_id` varchar(10) DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  `contacts` varchar(45) DEFAULT NULL,
  `fax` varchar(45) DEFAULT NULL,
  `telephone` varchar(45) DEFAULT NULL,
  `mobile` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `qq` varchar(45) DEFAULT NULL,
  `note` varchar(45) DEFAULT NULL,
  `update_time` datetime NOT NULL,
  `update_user` varchar(45) NOT NULL,
  PRIMARY KEY (`supplier_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `supplier`
--

LOCK TABLES `supplier` WRITE;
/*!40000 ALTER TABLE `supplier` DISABLE KEYS */;
INSERT INTO `supplier` VALUES ('201500000000001','生产商','一级供应商','天津日思优质小站稻开发公司','0000000003','0000000003','津南国家工业园区','刘经理','','','','','','','2015-06-08 12:04:29','管理员'),('201500000000002','生产商','一级供应商','天津津沽粮食工业有限公司','0000000003','0000000003','宁河县','王经理','','','','','','','2015-06-08 12:06:12','管理员'),('201500000000003','生产商','一级供应商','黄庄洼米业有限公司','0000000003','0000000003','宝坻区','许琨','','','','','','','2015-06-08 12:06:41','管理员'),('201500000000004','生产商','一级供应商','天津利民调料有限公司','0000000003','0000000003','空港经济区','刘洋','','','','','','','2015-06-08 12:07:40','管理员'),('201500000000005','生产商','一级供应商','天津市食品研究所有限公司','0000000003','0000000003','静海县','王锦','','','','','','','2015-06-08 12:08:18','管理员'),('201500000000006','生产商','一级供应商','天津正达蜂业有限公司','0000000003','0000000003','西青区','蔡莹','','','','','','','2015-06-08 12:08:48','管理员');
/*!40000 ALTER TABLE `supplier` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_sequence`
--

DROP TABLE IF EXISTS `tb_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_sequence` (
  `name` varchar(50) NOT NULL,
  `current_value` int(11) NOT NULL,
  `_increment` int(11) NOT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_sequence`
--

LOCK TABLES `tb_sequence` WRITE;
/*!40000 ALTER TABLE `tb_sequence` DISABLE KEYS */;
INSERT INTO `tb_sequence` VALUES ('brandId',7,1),('cartId',1,1),('chargeId',1,1),('commodityId',14,1),('distributorPriceId',5,1),('expressId',3,1),('expressPriceId',22,1),('placeOrderDetailId',3,1),('placeOrderId',3,1),('stockId',8,1),('supplierId',7,1);
/*!40000 ALTER TABLE `tb_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `user_id` varchar(45) NOT NULL,
  `user_name` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `id_card` varchar(45) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `gender` varchar(45) DEFAULT NULL,
  `company_name` varchar(45) DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `telephone` varchar(45) DEFAULT NULL,
  `department` varchar(45) DEFAULT NULL,
  `position` varchar(45) DEFAULT NULL,
  `update_time` datetime NOT NULL,
  `update_user` varchar(45) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('admin','管理员','21232f297a57a5a743894a0e4a801fc3','120','1980-01-11','男','天津','天津市西青区海泰科技园','test@test.com','1321','开发本部','架构师','2015-05-12 11:59:37','管理员');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-06-10 17:35:46
