CREATE DATABASE IF NOT EXISTS `university`;
USE university;

--
-- Create table `members`
--
CREATE TABLE IF NOT EXISTS `members` (
  `member_id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(50) DEFAULT NULL,
  `last_name` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `birthdate` date DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  `activityField` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`member_id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=10001 ;

--
-- Create table `students`
--
CREATE TABLE IF NOT EXISTS `students` (
  `student_id` int(11) NOT NULL AUTO_INCREMENT,
  `registration_number` varchar(50) DEFAULT NULL,
  `member_id` int(11) NOT NULL,
  PRIMARY KEY (`student_id`),
  FOREIGN KEY (`member_id`) REFERENCES `members`(`member_id`) ON DELETE CASCADE
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=10001 ;

--
-- Create table `professors`
--
CREATE TABLE IF NOT EXISTS `professors` (
  `professor_id` int(11) NOT NULL AUTO_INCREMENT,
  `phone_number` varchar(50) DEFAULT NULL,
  `member_id` int(11) NOT NULL,
  PRIMARY KEY (`professor_id`),
  FOREIGN KEY (`member_id`) REFERENCES `members`(`member_id`) ON DELETE CASCADE
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=10001 ;

--
-- Dumping data for table `members`
--
INSERT INTO `members` (`member_id`, `first_name`, `last_name`, `email`, `birthdate`, `status`, `activityField`) VALUES
(1, 'Diane', 'Jih', 'diane.jih@uqtr.ca', '1998-10-12', 'Actif', 'Informatique'),
(2, 'Rogers', 'Paul', 'roger.paul@uqtr.ca', '1987-01-05', 'Actif', 'Administration des affaires'),
(3, 'David', 'John', 'david.john@uqtr.ca', '2002-11-09', 'Actif', 'Education prescolaire'),
(4, 'Maria', 'Cisse', 'maria.cisse@uqtr.ca', '2007-12-25', 'Inactif', 'Art visuel'),
(5, 'Morris', 'Kone', 'morris.kone@uqtr.ca', '1990-07-08', 'Actif', 'Biochimie'),
(6, 'Mohamed', 'Bala', 'mohamed.bala@uqtr.ca', '1995-11-11', 'Actif', 'Biologie_medicale'),
(7, 'Sanders', 'Atangana', 'sanders.atangana@uqtr.ca', '1971-06-28', 'Inactif', 'Chimie'),
(8, 'Mark', 'Bouchard', 'marc.bouchard@uqtr.ca', '1988-02-11', 'Actif', 'Communication sociale'),
(9, 'Morgan', 'Couture', 'morgan.couture@uqtr.ca', '1997-03-15', 'Actif', 'Etudes francaise'),
(10, 'Paul', 'Miller', 'paul.miller@uqtr.ca', '1964-05-18', 'Actif', 'Genie electrique');

--
-- Dumping data for table `students`
--
INSERT INTO `students` (`student_id`, `registration_number`, `member_id`) VALUES
(1, 'DIAJ19981012', 1),
(2, 'DAVJ20021109', 3),
(3, 'MARC20071225', 4),
(4, 'MORK19900708', 5),
(5, 'MOHB19951111', 6),
(6, 'MARB19880211', 8),
(7, 'MORC19970315', 9);

--
-- Dumping data for table `professors`
--
INSERT INTO `professors` (`professor_id`, `phone_number`, `member_id`) VALUES
(1, '514 515 0569', 2),
(2, '873 333 5655', 7),
(3, '819 999 6060', 10);
