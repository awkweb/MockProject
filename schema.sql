CREATE SCHEMA IF NOT EXISTS `mockproject` DEFAULT CHARACTER SET latin1 ;
USE `mockproject` ;

-- -----------------------------------------------------
-- Table `mockproject`.`users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mockproject`.`users` ;

CREATE  TABLE IF NOT EXISTS `mockproject`.`users` (
  `user_id` BIGINT(20) NOT NULL ,
  `email` VARCHAR(40) NOT NULL ,
  `f_name` VARCHAR(20) NOT NULL ,
  `l_name` VARCHAR(20) NOT NULL ,
  `password` VARCHAR(20) NOT NULL ,
  `role` VARCHAR(20) NOT NULL ,
  `username` VARCHAR(20) NOT NULL ,
  `traderId` VARCHAR(255) NULL DEFAULT NULL ,
  PRIMARY KEY (`user_id`) )
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `mockproject`.`blocks`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mockproject`.`blocks` ;

CREATE  TABLE IF NOT EXISTS `mockproject`.`blocks` (
  `block_id` BIGINT(20) NOT NULL AUTO_INCREMENT ,
  `status` VARCHAR(20) NOT NULL ,
  `symbol` VARCHAR(45) NOT NULL ,
  `side` VARCHAR(45) NOT NULL ,
  `timestamp` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP ,
  `total_qty` INT(11) NOT NULL ,
  `limit_price` FLOAT(5,2) NULL DEFAULT NULL ,
  `stop_price` FLOAT(5,2) NULL DEFAULT NULL ,
  `traderid` BIGINT(20) NOT NULL ,
  `executed_qty` INT(11) NULL DEFAULT NULL ,
  `open_qty` INT(11) NULL DEFAULT NULL ,
  PRIMARY KEY (`block_id`) ,
  INDEX `fk_blocks_users` (`traderid` ASC) ,
  INDEX `fk_blocks_traderid` (`traderid` ASC) ,
  INDEX `FK_pkcioe3xcqyx35eoqq94475j0` (`traderid` ASC) ,
  CONSTRAINT `FK_pkcioe3xcqyx35eoqq94475j0`
    FOREIGN KEY (`traderid` )
    REFERENCES `mockproject`.`users` (`user_id` ))
AUTO_INCREMENT = 201
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `mockproject`.`securities`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mockproject`.`securities` ;

CREATE  TABLE IF NOT EXISTS `mockproject`.`securities` (
  `symbol` VARCHAR(20) NOT NULL ,
  `market_price` FLOAT(5,2) NOT NULL ,
  `name` VARCHAR(50) NOT NULL ,
  `closing_price` FLOAT NULL DEFAULT NULL ,
  PRIMARY KEY (`symbol`) )
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `mockproject`.`executeblocks`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mockproject`.`executeblocks` ;

CREATE  TABLE IF NOT EXISTS `mockproject`.`executeblocks` (
  `execution_id` BIGINT(20) NOT NULL AUTO_INCREMENT ,
  `allocated_qty` INT(11) NULL DEFAULT '0' ,
  `average_price` FLOAT(5,2) NULL DEFAULT NULL ,
  `blockid` BIGINT(20) NOT NULL ,
  `executed_qty` INT(11) NULL DEFAULT NULL ,
  `remaining_qty` INT(11) NULL DEFAULT NULL ,
  `side` VARCHAR(20) NOT NULL ,
  `symbol` VARCHAR(5) NOT NULL ,
  `status` VARCHAR(20) NOT NULL ,
  `trade_price` FLOAT(5,2) NULL DEFAULT NULL ,
  `transact_fee` FLOAT(5,2) NULL DEFAULT NULL ,
  `transaction_time` TIMESTAMP NULL DEFAULT NULL ,
  `order_id` VARCHAR(255) NULL DEFAULT NULL ,
  `orderid` VARCHAR(255) NULL DEFAULT NULL ,
  `totalquantity` INT(11) NULL DEFAULT NULL ,
  PRIMARY KEY (`execution_id`) ,
  INDEX `fk_executedblocks_blocks` (`blockid` ASC) ,
  INDEX `fk_symbol` (`symbol` ASC) ,
  INDEX `fk_executedblocks_blockid` (`blockid` ASC) ,
  INDEX `fk_executeblocks_blockid` (`blockid` ASC) ,
  INDEX `FK_hawmbb9t854oyk42759up724a` (`symbol` ASC) ,
  CONSTRAINT `fk_executeblocks_blockid`
    FOREIGN KEY (`blockid` )
    REFERENCES `mockproject`.`blocks` (`block_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_hawmbb9t854oyk42759up724a`
    FOREIGN KEY (`symbol` )
    REFERENCES `mockproject`.`securities` (`symbol` ))
AUTO_INCREMENT = 1626613
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `mockproject`.`portfolios`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mockproject`.`portfolios` ;

CREATE  TABLE IF NOT EXISTS `mockproject`.`portfolios` (
  `port_id` BIGINT(20) NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(20) NOT NULL ,
  `pm_id` BIGINT(20) NOT NULL ,
  `pmID` VARCHAR(255) NULL DEFAULT NULL ,
  PRIMARY KEY (`port_id`) ,
  INDEX `fk_portfolios_users` (`pm_id` ASC) ,
  INDEX `fk_portfolios_pmid` (`pm_id` ASC) ,
  CONSTRAINT `fk_portfolios_pmid`
    FOREIGN KEY (`pm_id` )
    REFERENCES `mockproject`.`users` (`user_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `mockproject`.`orders`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mockproject`.`orders` ;

CREATE  TABLE IF NOT EXISTS `mockproject`.`orders` (
  `order_id` BIGINT(20) NOT NULL AUTO_INCREMENT ,
  `symbol` VARCHAR(5) NOT NULL ,
  `total_qty` INT(11) NOT NULL ,
  `limit_price` FLOAT(5,2) NULL DEFAULT NULL ,
  `stop_price` FLOAT(5,2) NULL DEFAULT NULL ,
  `side` VARCHAR(4) NOT NULL ,
  `open_qty` INT(11) NULL DEFAULT NULL ,
  `alloc_qty` INT(11) NULL DEFAULT NULL ,
  `status` VARCHAR(20) NOT NULL ,
  `timestamp` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  `ordertype` VARCHAR(20) NOT NULL ,
  `pmid` BIGINT(20) NOT NULL ,
  `traderid` BIGINT(20) NOT NULL ,
  `blockid` BIGINT(20) NULL DEFAULT NULL ,
  `notes` VARCHAR(140) NULL DEFAULT NULL ,
  `qualifiers` VARCHAR(10) NULL DEFAULT NULL ,
  `acc_type` VARCHAR(20) NULL DEFAULT NULL ,
  `portfolioid` BIGINT(20) NOT NULL ,
  `orderid` INT(11) NOT NULL ,
  PRIMARY KEY (`order_id`) ,
  INDEX `fk_order_users1` (`pmid` ASC) ,
  INDEX `fk_order_securities` (`symbol` ASC) ,
  INDEX `fk_order_portfolios` (`portfolioid` ASC) ,
  INDEX `fk_order_pmid` (`pmid` ASC) ,
  INDEX `fk_order_trader` (`traderid` ASC) ,
  INDEX `fk_orders_pmid` (`pmid` ASC) ,
  INDEX `fk_order_traderid` (`traderid` ASC) ,
  INDEX `fk_order_portid` (`portfolioid` ASC) ,
  INDEX `fk_order_blockid` (`blockid` ASC) ,
  INDEX `FK_hmsk25beh6atojvle1xuymjj0` (`order_id` ASC) ,
  INDEX `fk_order_symbol` (`symbol` ASC) ,
  CONSTRAINT `fk_order_symbol`
    FOREIGN KEY (`symbol` )
    REFERENCES `mockproject`.`securities` (`symbol` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_orders_pmid`
    FOREIGN KEY (`pmid` )
    REFERENCES `mockproject`.`users` (`user_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_order_blockid`
    FOREIGN KEY (`blockid` )
    REFERENCES `mockproject`.`blocks` (`block_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_order_portid`
    FOREIGN KEY (`portfolioid` )
    REFERENCES `mockproject`.`portfolios` (`port_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_order_traderid`
    FOREIGN KEY (`traderid` )
    REFERENCES `mockproject`.`users` (`user_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
AUTO_INCREMENT = 100
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `mockproject`.`positions`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mockproject`.`positions` ;

CREATE  TABLE IF NOT EXISTS `mockproject`.`positions` (
  `position_id` BIGINT(20) NOT NULL AUTO_INCREMENT ,
  `orderid` BIGINT(20) NOT NULL ,
  `portid` BIGINT(20) NOT NULL ,
  `order_id` VARCHAR(255) NULL DEFAULT NULL ,
  `port_id` VARCHAR(255) NULL DEFAULT NULL ,
  PRIMARY KEY (`position_id`) ,
  INDEX `fk_positions_order` (`orderid` ASC) ,
  INDEX `fk_positions_portfolios` (`portid` ASC) ,
  INDEX `fk_positions_orderid` (`orderid` ASC) ,
  INDEX `fk_positions_portid` (`portid` ASC) ,
  CONSTRAINT `fk_positions_orderid`
    FOREIGN KEY (`orderid` )
    REFERENCES `mockproject`.`orders` (`order_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_positions_portid`
    FOREIGN KEY (`portid` )
    REFERENCES `mockproject`.`portfolios` (`port_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `mockproject`.`testorder`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mockproject`.`testorder` ;

CREATE  TABLE IF NOT EXISTS `mockproject`.`testorder` (
  `order_id` INT(11) NOT NULL ,
  `status` VARCHAR(255) NULL DEFAULT NULL ,
  `symbol` VARCHAR(255) NULL DEFAULT NULL ,
  PRIMARY KEY (`order_id`) )
DEFAULT CHARACTER SET = latin1;

