ALTER TABLE `visit`
  ADD `numberOfWordsOfThanks` INT;

  ALTER TABLE `visit`
  ADD FOREIGN KEY (`patient_id`) REFERENCES `personalData`(`id`);