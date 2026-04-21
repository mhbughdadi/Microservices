-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema products_ms_db
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema products_ms_db
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `products_ms_db` DEFAULT CHARACTER SET utf16 ;
USE `products_ms_db` ;

-- -----------------------------------------------------
-- Table `products_ms_db`.`parent_item`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `products_ms_db`.`parent_item` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `created_by` VARCHAR(100) NULL DEFAULT NULL,
  `created_at` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_by` VARCHAR(100) NULL DEFAULT NULL,
  `updated_at` DATETIME NULL DEFAULT NULL,
  `deleted` TINYINT(1) NULL DEFAULT '0',
  `entity_type` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 44
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `products_ms_db`.`products`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `products_ms_db`.`products` (
  `code` VARCHAR(100) NOT NULL,
  `name_ar` VARCHAR(255) NULL DEFAULT NULL,
  `name_en` VARCHAR(255) NULL DEFAULT NULL,
  `description_ar` TEXT NULL DEFAULT NULL,
  `description_en` TEXT NULL DEFAULT NULL,
  `active` TINYINT(1) NULL DEFAULT '1',
  `brand` VARCHAR(255) NULL DEFAULT NULL,
  `manufacturer_code` VARCHAR(100) NULL DEFAULT NULL,
  `warranty_info_en` TEXT NULL DEFAULT NULL,
  `warranty_info_ar` TEXT NULL DEFAULT NULL,
  `return_policy_ar` TEXT NULL DEFAULT NULL,
  `return_policy_en` TEXT NULL DEFAULT NULL,
  `available_from` TEXT NULL DEFAULT NULL,
  `status` ENUM('ACTIVE', 'INACTIVE', 'DRAFT', 'DISCONTINUED') NULL DEFAULT NULL,
  `default_currency` VARCHAR(10) NULL DEFAULT NULL,
  `is_featured` TINYINT NULL DEFAULT '0',
  `seo_title_en` VARCHAR(255) NULL DEFAULT NULL,
  `seo_title_ar` VARCHAR(255) NULL DEFAULT NULL,
  `seo_description_en` TEXT NULL DEFAULT NULL,
  `seo_description_ar` TEXT NULL DEFAULT NULL,
  `view_count` INT NULL DEFAULT NULL,
  `rating_avg` DECIMAL(3,2) NULL DEFAULT NULL,
  `review_count` INT NULL DEFAULT NULL,
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `idx_product_active` (`active` ASC) VISIBLE,
  INDEX `fk_products_auditable_item1_idx` (`id` ASC) VISIBLE,
  CONSTRAINT `fk_products_auditable_item1`
    FOREIGN KEY (`id`)
    REFERENCES `products_ms_db`.`parent_item` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 43
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `products_ms_db`.`skus`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `products_ms_db`.`skus` (
  `sku_code` VARCHAR(100) NOT NULL,
  `title_ar` VARCHAR(255) NULL DEFAULT NULL,
  `title_en` VARCHAR(255) NULL DEFAULT NULL,
  `subtitle_ar` VARCHAR(255) NULL DEFAULT NULL,
  `subtitle_en` VARCHAR(255) NULL DEFAULT NULL,
  `description_ar` TEXT NULL DEFAULT NULL,
  `description_en` TEXT NULL DEFAULT NULL,
  `price` DECIMAL(10,2) NULL DEFAULT NULL,
  `active` TINYINT(1) NULL DEFAULT '1',
  `status` ENUM('ACTIVE', 'INACTIVE', 'DRAFT', 'DISCONTINUED') NULL DEFAULT NULL,
  `long_description_ar` VARCHAR(200) NULL DEFAULT NULL,
  `long_description_en` VARCHAR(200) NULL DEFAULT NULL,
  `available_from` DATE NULL DEFAULT NULL,
  `available_until` DATE NULL DEFAULT NULL,
  `is_digital` TINYINT NULL DEFAULT NULL,
  `is_shippable` TINYINT NULL DEFAULT NULL,
  `weight_grams` INT NULL DEFAULT NULL,
  `dimensions` VARCHAR(100) NULL DEFAULT NULL,
  `stock_quantity` INT NULL DEFAULT NULL,
  `barcode` VARCHAR(100) NULL DEFAULT NULL,
  `attributes` JSON NULL DEFAULT NULL,
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `product_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `idx_sku_active` (`active` ASC) VISIBLE,
  INDEX `fk_skus_auditable_item1_idx` (`id` ASC) VISIBLE,
  INDEX `fk_skus_products1_idx` (`product_id` ASC) VISIBLE,
  CONSTRAINT `fk_skus_auditable_item1`
    FOREIGN KEY (`id`)
    REFERENCES `products_ms_db`.`parent_item` (`id`),
  CONSTRAINT `fk_skus_products1`
    FOREIGN KEY (`product_id`)
    REFERENCES `products_ms_db`.`products` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 41
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `products_ms_db`.`benefits`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `products_ms_db`.`benefits` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `key_ar` VARCHAR(100) NULL DEFAULT NULL,
  `value_ar` TEXT NULL DEFAULT NULL,
  `key_en` VARCHAR(100) NULL DEFAULT NULL,
  `value_en` TEXT NULL DEFAULT NULL,
  `image_url` VARCHAR(100) NULL DEFAULT NULL,
  `sku_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_benefits_skus1_idx` (`sku_id` ASC) VISIBLE,
  CONSTRAINT `fk_benefits_skus1`
    FOREIGN KEY (`sku_id`)
    REFERENCES `products_ms_db`.`skus` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `products_ms_db`.`categories`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `products_ms_db`.`categories` (
  `code` VARCHAR(100) NOT NULL,
  `name_ar` VARCHAR(255) NULL DEFAULT NULL,
  `name_en` VARCHAR(255) NULL DEFAULT NULL,
  `description_ar` TEXT NULL DEFAULT NULL,
  `description_en` TEXT NULL DEFAULT NULL,
  `parent_id` BIGINT NULL DEFAULT NULL,
  `active` TINYINT(1) NULL DEFAULT '1',
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `idx_category_active` (`active` ASC) VISIBLE,
  INDEX `fk_categories_auditable_item1_idx` (`id` ASC) VISIBLE,
  CONSTRAINT `fk_categories_auditable_item1`
    FOREIGN KEY (`id`)
    REFERENCES `products_ms_db`.`parent_item` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 44
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `products_ms_db`.`images`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `products_ms_db`.`images` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `url` VARCHAR(500) NOT NULL,
  `alt_text_en` VARCHAR(255) NULL DEFAULT NULL,
  `alt_text_ar` VARCHAR(255) NULL DEFAULT NULL,
  `created_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `products_ms_db`.`tags`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `products_ms_db`.`tags` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `description` VARCHAR(255) NULL DEFAULT NULL,
  `created_at` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  `description_ar` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 10
DEFAULT CHARACTER SET = utf16;


-- -----------------------------------------------------
-- Table `products_ms_db`.`item_tags`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `products_ms_db`.`item_tags` (
  `tag_id` BIGINT NOT NULL,
  `item_id` BIGINT NOT NULL,
  PRIMARY KEY (`tag_id`, `item_id`),
  INDEX `fk_tag_assignments_tags_idx` (`tag_id` ASC) VISIBLE,
  INDEX `fk_tag_assignments_auditable_item1_idx` (`item_id` ASC) VISIBLE,
  CONSTRAINT `fk_tag_assignments_auditable_item1`
    FOREIGN KEY (`item_id`)
    REFERENCES `products_ms_db`.`parent_item` (`id`),
  CONSTRAINT `fk_tag_assignments_tags`
    FOREIGN KEY (`tag_id`)
    REFERENCES `products_ms_db`.`tags` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf16;


-- -----------------------------------------------------
-- Table `products_ms_db`.`parent_images`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `products_ms_db`.`parent_images` (
  `type` ENUM('HERO', 'GALLERY', 'VARIANT', 'LOGO', 'BADGE') CHARACTER SET 'utf16' NULL DEFAULT NULL,
  `sort_order` INT NULL DEFAULT NULL,
  `is_active` DOUBLE NULL DEFAULT NULL,
  `uploaded_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `image_id` BIGINT NOT NULL,
  `parent_id` BIGINT NOT NULL,
  PRIMARY KEY (`parent_id`, `image_id`),
  INDEX `image_id_idx` (`image_id` ASC) VISIBLE,
  CONSTRAINT `image_id_fk`
    FOREIGN KEY (`image_id`)
    REFERENCES `products_ms_db`.`images` (`id`),
  CONSTRAINT `parent_id_fk`
    FOREIGN KEY (`parent_id`)
    REFERENCES `products_ms_db`.`parent_item` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf32;


-- -----------------------------------------------------
-- Table `products_ms_db`.`product_categories`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `products_ms_db`.`product_categories` (
  `product_id` BIGINT NOT NULL,
  `category_id` BIGINT NOT NULL,
  PRIMARY KEY (`product_id`, `category_id`),
  INDEX `fk_product_categories_products1_idx` (`product_id` ASC) VISIBLE,
  INDEX `fk_product_categories_categories1_idx` (`category_id` ASC) VISIBLE,
  CONSTRAINT `fk_product_categories_categories1`
    FOREIGN KEY (`category_id`)
    REFERENCES `products_ms_db`.`categories` (`id`),
  CONSTRAINT `fk_product_categories_products1`
    FOREIGN KEY (`product_id`)
    REFERENCES `products_ms_db`.`products` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
