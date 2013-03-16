ALTER TABLE `frcscout`.`match_record_2013` DROP FOREIGN KEY `FK_matchid` ;
ALTER TABLE `frcscout`.`match_record_2013` DROP COLUMN `match_id` 
, DROP INDEX `id_idx1` ;

ALTER TABLE `frcscout`.`match` DROP COLUMN `id` 
, DROP PRIMARY KEY 
, ADD PRIMARY KEY (`match_number`, `event_id`) 
, DROP INDEX `id_UNIQUE` ;

ALTER TABLE `frcscout`.`match_record_2013` ADD COLUMN `event_id` INT NOT NULL  AFTER `user` , 
ADD COLUMN `match_number` INT NOT NULL  AFTER `event_id` ;

ALTER TABLE `frcscout`.`match_record_2013` 
  ADD CONSTRAINT `FK_event`
  FOREIGN KEY (`event_id` )
  REFERENCES `frcscout`.`events` (`id` )
  ON DELETE NO ACTION
  ON UPDATE NO ACTION, 
  ADD CONSTRAINT `FK_matchid`
  FOREIGN KEY (`match_number` )
  REFERENCES `frcscout`.`match` (`match_number` )
  ON DELETE NO ACTION
  ON UPDATE NO ACTION
, ADD INDEX `FK_event_idx` (`event_id` ASC) 
, ADD INDEX `FK_matchid_idx` (`match_number` ASC) ;
