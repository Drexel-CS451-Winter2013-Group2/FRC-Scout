ALTER TABLE `frcscout`.`users` ADD COLUMN `id` INT NOT NULL AUTO_INCREMENT  AFTER `email` 
, ADD UNIQUE INDEX `id_UNIQUE` (`id` ASC) ;

ALTER TABLE `frcscout`.`users` ADD COLUMN `active` TINYINT(1) NOT NULL DEFAULT 1  AFTER `last_name` ;