--
-- Create table `professors`
--
CREATE TABLE IF NOT EXISTS `professors` (
  `first_name` varchar(50) DEFAULT NULL,
  `last_name` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `birthdate` date DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  `activityField` varchar(50) DEFAULT NULL,
  `phone_number` varchar(50) DEFAULT NULL,
  `registration_number` varchar(50) NOT NULL,
  PRIMARY KEY (`registration_number`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=10001 ;

--
-- Dumping data for table `professors`
--
INSERT INTO `professors` (`first_name`, `last_name`, `email`, `birthdate`, `status`, `activityField`, `phone_number`, registration_number) VALUES
('Rogers', 'Paul', 'roger.paul@uqtr.ca', '1987-01-05', 'Actif', 'Administration des affaires', '8515256369', 'ROGP19870105'),
('Sanders', 'Atangana', 'sanders.atangana@uqtr.ca', '1971-06-28', 'Inactif', 'Chimie', '4186963369', 'SANA19710628'),
('Paul', 'Miller', 'paul.miller@uqtr.ca', '1964-05-18', 'Actif', 'Genie electrique', '8736963345', 'PAUM19640518');
