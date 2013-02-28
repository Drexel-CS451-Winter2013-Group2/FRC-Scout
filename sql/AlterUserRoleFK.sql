ALTER TABLE `frcscout`.`user_roles` DROP FOREIGN KEY `FK_email` ;
ALTER TABLE `frcscout`.`user_roles` 
  ADD CONSTRAINT `FK_email`
  FOREIGN KEY (`email` )
  REFERENCES `frcscout`.`users` (`email` )
  ON DELETE CASCADE
  ON UPDATE CASCADE;

ALTER TABLE `frcscout`.`user_roles` DROP FOREIGN KEY `FK_roles` ;
ALTER TABLE `frcscout`.`user_roles` 
  ADD CONSTRAINT `FK_roles`
  FOREIGN KEY (`roles` )
  REFERENCES `frcscout`.`roles` (`name` )
  ON DELETE CASCADE
  ON UPDATE CASCADE;
