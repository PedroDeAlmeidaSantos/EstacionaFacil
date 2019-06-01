-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema db_estaciona_facil
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema db_estaciona_facil
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `db_estaciona_facil` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `db_estaciona_facil` ;

-- -----------------------------------------------------
-- Table `db_estaciona_facil`.`tbl_movimentacao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_estaciona_facil`.`tbl_movimentacao` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `placa` VARCHAR(10) NOT NULL,
  `modelo` VARCHAR(50) NOT NULL,
  `horario_entrada` DATETIME NOT NULL,
  `horario_saida` DATETIME NULL,
  `tempo` DATETIME NULL,
  `valor_pago` FLOAT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db_estaciona_facil`.`tbl_preco`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_estaciona_facil`.`tbl_preco` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `valor_primeira_hora` FLOAT NOT NULL,
  `valor_demais_horas` FLOAT NOT NULL,
  `data_fim` DATE NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
