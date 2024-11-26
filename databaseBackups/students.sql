--
-- Create table `students`
--
CREATE TABLE IF NOT EXISTS `students` (
  `first_name` varchar(50) DEFAULT NULL,
  `last_name` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `birthdate` date DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  `activityField` varchar(50) DEFAULT NULL,
  `registration_number` varchar(50) NOT NULL,
  PRIMARY KEY (`registration_number`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=10001 ;

--
-- Dumping data for table `members`
--
INSERT INTO `students` (`first_name`, `last_name`, `email`, `birthdate`, `status`, `activityField`, `registration_number`) VALUES
( 'Diane', 'Jih', 'diane.jih@uqtr.ca', '1998-10-12', 'Actif', 'Informatique', 'DIAJ19981012'),
( 'David', 'John', 'david.john@uqtr.ca', '2002-11-09', 'Actif', 'Education prescolaire', 'DAVJ20021109'),
( 'Maria', 'Cisse', 'maria.cisse@uqtr.ca', '2007-12-25', 'Inactif', 'Art visuel', 'MARC20071225'),
( 'Morris', 'Kone', 'morris.kone@uqtr.ca', '1990-07-08', 'Actif', 'Biochimie', 'MORK19900708'),
( 'Mohamed', 'Bala', 'mohamed.bala@uqtr.ca', '1995-11-11', 'Actif', 'Biologie_medicale', 'MOHB19951111'),
( 'Mark', 'Bouchard', 'marc.bouchard@uqtr.ca', '1988-02-11', 'Actif', 'Communication sociale', 'MARB19880211'),
( 'Morgan', 'Couture', 'morgan.couture@uqtr.ca', '1997-03-15', 'Actif', 'Etudes francaise', 'MORC19970315');
