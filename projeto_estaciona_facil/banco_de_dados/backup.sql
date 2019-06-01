-- MySQL dump 10.13  Distrib 8.0.11, for Win64 (x86_64)
--
-- Host: localhost    Database: db_estaciona_facil
-- ------------------------------------------------------
-- Server version	8.0.11

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `tbl_fabricante`
--

DROP TABLE IF EXISTS `tbl_fabricante`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tbl_fabricante` (
  `cod_fabricante` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(30) NOT NULL,
  PRIMARY KEY (`cod_fabricante`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_fabricante`
--

LOCK TABLES `tbl_fabricante` WRITE;
/*!40000 ALTER TABLE `tbl_fabricante` DISABLE KEYS */;
INSERT INTO `tbl_fabricante` VALUES (1,'VW'),(2,'GM');
/*!40000 ALTER TABLE `tbl_fabricante` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_mensalista`
--

DROP TABLE IF EXISTS `tbl_mensalista`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tbl_mensalista` (
  `id_mensalista` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) NOT NULL,
  `cpf` varchar(15) NOT NULL,
  `celular` varchar(20) NOT NULL,
  `qtd_vagas` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_mensalista`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_mensalista`
--

LOCK TABLES `tbl_mensalista` WRITE;
/*!40000 ALTER TABLE `tbl_mensalista` DISABLE KEYS */;
INSERT INTO `tbl_mensalista` VALUES (1,'123','321','123',3);
/*!40000 ALTER TABLE `tbl_mensalista` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_movimentacao`
--

DROP TABLE IF EXISTS `tbl_movimentacao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tbl_movimentacao` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `placa` varchar(10) NOT NULL,
  `modelo` varchar(50) DEFAULT NULL,
  `horario_entrada` datetime DEFAULT NULL,
  `horario_saida` datetime DEFAULT NULL,
  `tempo` int(11) DEFAULT NULL,
  `valor_pago` float DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_movimentacao`
--

LOCK TABLES `tbl_movimentacao` WRITE;
/*!40000 ALTER TABLE `tbl_movimentacao` DISABLE KEYS */;
INSERT INTO `tbl_movimentacao` VALUES (23,'PEDRO','PEDRO','2019-05-15 16:24:39','2019-05-15 16:25:07',NULL,0),(24,'123','321','2019-05-16 13:24:11','2019-05-16 13:34:33',NULL,0),(25,'kkk','kkk','2019-05-16 15:39:26',NULL,NULL,NULL),(26,'aaaa','aaaa','2019-05-16 15:40:46','2019-05-22 13:21:50',NULL,715),(27,'1234','4321','2019-05-22 16:06:36','2019-05-22 16:09:45',NULL,0),(28,'abc-1234','gol','2019-05-23 13:06:50',NULL,NULL,NULL),(29,'888','888','2019-05-23 13:07:26',NULL,NULL,NULL);
/*!40000 ALTER TABLE `tbl_movimentacao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_preco`
--

DROP TABLE IF EXISTS `tbl_preco`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tbl_preco` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `valor_primeira_hora` float NOT NULL,
  `valor_demais_horas` float NOT NULL,
  `data_fim` date DEFAULT NULL,
  `tolerancia` int(11) DEFAULT NULL,
  `flag` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_preco`
--

LOCK TABLES `tbl_preco` WRITE;
/*!40000 ALTER TABLE `tbl_preco` DISABLE KEYS */;
INSERT INTO `tbl_preco` VALUES (1,10,5,'2019-05-22',0,'NV'),(2,20,10,'2019-05-22',0,'NV'),(3,4,54,'2019-05-22',0,'NV'),(4,10,5,'2019-05-22',0,'NV'),(5,10,5,NULL,5,'V');
/*!40000 ALTER TABLE `tbl_preco` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_veiculo`
--

DROP TABLE IF EXISTS `tbl_veiculo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tbl_veiculo` (
  `id_veiculo` int(11) NOT NULL,
  `placa` varchar(8) NOT NULL,
  `modelo` varchar(30) NOT NULL,
  `id_mensalista` int(11) NOT NULL,
  PRIMARY KEY (`id_veiculo`),
  KEY `id_mensalista` (`id_mensalista`),
  CONSTRAINT `tbl_veiculo_ibfk_1` FOREIGN KEY (`id_mensalista`) REFERENCES `tbl_mensalista` (`id_mensalista`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_veiculo`
--

LOCK TABLES `tbl_veiculo` WRITE;
/*!40000 ALTER TABLE `tbl_veiculo` DISABLE KEYS */;
INSERT INTO `tbl_veiculo` VALUES (1,'12345678','321',1);
/*!40000 ALTER TABLE `tbl_veiculo` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-05-23 16:44:34
