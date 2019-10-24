-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema Aerolinea
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema Aerolinea
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `Aerolinea` DEFAULT CHARACTER SET utf8 ;
USE `Aerolinea` ;

-- -----------------------------------------------------
-- Table `Aerolinea`.`Pais`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Aerolinea`.`Pais` (
  `pais` VARCHAR(9) NOT NULL,
  `nombre` VARCHAR(45) NULL,
  PRIMARY KEY (`pais`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Aerolinea`.`Ciudad`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Aerolinea`.`Ciudad` (
  `ciudad` VARCHAR(9) NOT NULL,
  `nombre` VARCHAR(45) NULL,
  `pais` VARCHAR(9) NOT NULL,
  PRIMARY KEY (`ciudad`),
  INDEX `fk_Ciudad_Pais1_idx` (`pais` ASC)  ,
  CONSTRAINT `fk_Ciudad_Pais1`
    FOREIGN KEY (`pais`)
    REFERENCES `Aerolinea`.`Pais` (`pais`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Aerolinea`.`Ruta`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Aerolinea`.`Ruta` (
  `ruta` INT NOT NULL AUTO_INCREMENT,
  `duracion` TIME NULL,
  `ciudad_salida` VARCHAR(45) NOT NULL,
  `ciudad_llegada` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`ruta`),
  INDEX `fk_Ruta_Ciudad1_idx` (`ciudad_salida` ASC)  ,
  INDEX `fk_Ruta_Ciudad2_idx` (`ciudad_llegada` ASC)  ,
  CONSTRAINT `fk_Ruta_Ciudad1`
    FOREIGN KEY (`ciudad_salida`)
    REFERENCES `Aerolinea`.`Ciudad` (`ciudad`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Ruta_Ciudad2`
    FOREIGN KEY (`ciudad_llegada`)
    REFERENCES `Aerolinea`.`Ciudad` (`ciudad`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Aerolinea`.`Horario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Aerolinea`.`Horario` (
  `horario` VARCHAR(45) NOT NULL,
  `salida` TIME NULL,
  `llegada` TIME NULL,
  `dia_de_la_semana` VARCHAR(45) NULL,
  PRIMARY KEY (`horario`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Aerolinea`.`Avion_Disponible`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Aerolinea`.`Avion_Disponible` (
  `codigo_matricula` VARCHAR(12) NOT NULL,
  `ano` YEAR NULL,
  `modelo` VARCHAR(45) NULL,
  `marca` VARCHAR(45) NULL,
  `filas` INT NULL,
  `columnas` INT NULL,
  `cantidad_de_pasajeros` INT NULL,
  PRIMARY KEY (`codigo_matricula`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Aerolinea`.`Vuelo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Aerolinea`.`Vuelo` (
  `vuelo` VARCHAR(12) NOT NULL,
  `horario` VARCHAR(12) NOT NULL,
  `avion_asignado` VARCHAR(12) NOT NULL,
  `ruta_asignada` INT NOT NULL,
  PRIMARY KEY (`vuelo`),
  INDEX `fk_Vuelo_Horario1_idx` (`horario` ASC)  ,
  INDEX `fk_Vuelo_Avion_Disponible1_idx` (`avion_asignado` ASC)  ,
  INDEX `fk_Vuelo_Ruta1_idx` (`ruta_asignada` ASC)  ,
  CONSTRAINT `fk_Vuelo_Horario1`
    FOREIGN KEY (`horario`)
    REFERENCES `Aerolinea`.`Horario` (`horario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Vuelo_Avion_Disponible1`
    FOREIGN KEY (`avion_asignado`)
    REFERENCES `Aerolinea`.`Avion_Disponible` (`codigo_matricula`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Vuelo_Ruta1`
    FOREIGN KEY (`ruta_asignada`)
    REFERENCES `Aerolinea`.`Ruta` (`ruta`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Aerolinea`.`Usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Aerolinea`.`Usuario` (
  `usuario` VARCHAR(45) NOT NULL,
  `contrasena` VARCHAR(45) NULL,
  `nombre` VARCHAR(45) NULL,
  `apellidos` VARCHAR(45) NULL,
  `correo_electronico` VARCHAR(45) NULL,
  `fecha_nacimiento` DATE NULL,
  `direccion` VARCHAR(150) NULL,
  `telefono` VARCHAR(20) NULL,
  `celular` VARCHAR(20) NULL,
  `admin` TINYINT NULL,
  PRIMARY KEY (`usuario`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Aerolinea`.`Ticket`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Aerolinea`.`Ticket` (
  `ticket` INT NOT NULL AUTO_INCREMENT,
  `precio` DOUBLE NULL,
  `fecha_venta` DATETIME NULL,
  PRIMARY KEY (`ticket`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Aerolinea`.`Viaje`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Aerolinea`.`Viaje` (
  `viaje` INT NOT NULL,
  `vuelo` VARCHAR(12) NOT NULL,
  `fecha` DATE NULL,
  PRIMARY KEY (`viaje`),
  INDEX `fk_Viaje_Vuelo1_idx` (`vuelo` ASC)  ,
  CONSTRAINT `fk_Viaje_Vuelo1`
    FOREIGN KEY (`vuelo`)
    REFERENCES `Aerolinea`.`Vuelo` (`vuelo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Aerolinea`.`Tipo_de_Pago`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Aerolinea`.`Tipo_de_Pago` (
  `tipo_de_pago` VARCHAR(9) NOT NULL,
  `descripcion` VARCHAR(45) NULL,
  PRIMARY KEY (`tipo_de_pago`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Aerolinea`.`Pago`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Aerolinea`.`Pago` (
  `pago` INT NOT NULL,
  `monto` DOUBLE NULL,
  `fecha` DATE NULL,
  `TipoPago_codigo_pago` VARCHAR(9) NOT NULL,
  PRIMARY KEY (`pago`),
  INDEX `fk_Pago_TipoPago1_idx` (`TipoPago_codigo_pago` ASC)  ,
  CONSTRAINT `fk_Pago_TipoPago1`
    FOREIGN KEY (`TipoPago_codigo_pago`)
    REFERENCES `Aerolinea`.`Tipo_de_Pago` (`tipo_de_pago`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Aerolinea`.`Reserva`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Aerolinea`.`Reserva` (
  `reserva` INT NOT NULL AUTO_INCREMENT,
  `viaje` INT NOT NULL,
  `numero_asiento` VARCHAR(45) NULL,
  `pago` INT NOT NULL,
  `usuario` VARCHAR(45) NOT NULL,
  `ticket` INT NOT NULL,
  PRIMARY KEY (`reserva`),
  INDEX `fk_Reserva_Viajes1_idx` (`viaje` ASC)  ,
  INDEX `fk_Reserva_Pago1_idx` (`pago` ASC)  ,
  INDEX `fk_Reserva_Usuario1_idx` (`usuario` ASC)  ,
  INDEX `fk_Reserva_Ticket1_idx` (`ticket` ASC)  ,
  CONSTRAINT `fk_Reserva_Viajes1`
    FOREIGN KEY (`viaje`)
    REFERENCES `Aerolinea`.`Viaje` (`viaje`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Reserva_Pago1`
    FOREIGN KEY (`pago`)
    REFERENCES `Aerolinea`.`Pago` (`pago`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Reserva_Usuario1`
    FOREIGN KEY (`usuario`)
    REFERENCES `Aerolinea`.`Usuario` (`usuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Reserva_Ticket1`
    FOREIGN KEY (`ticket`)
    REFERENCES `Aerolinea`.`Ticket` (`ticket`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
