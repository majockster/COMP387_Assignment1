-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 04, 2022 at 05:06 AM
-- Server version: 10.4.24-MariaDB
-- PHP Version: 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `soen387a1`
--
CREATE DATABASE IF NOT EXISTS `soen387a1` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `soen387a1`;

-- Drop tables
DROP TABLE IF EXISTS `registrations`;
DROP TABLE IF EXISTS `coursetimes`;
DROP TABLE IF EXISTS `administrator`;
DROP TABLE IF EXISTS `student`;
DROP TABLE IF EXISTS `courses`;
DROP TABLE IF EXISTS `person`;

-- --------------------------------------------------------

--
-- Table structure for table `administrator`
--

CREATE TABLE `administrator` (
  `employmentID` int(11) NOT NULL,
  `personID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- RELATIONSHIPS FOR TABLE `administrator`:
--   `personID`
--       `person` -> `personID`
--

--
-- Truncate table before insert `administrator`
--

TRUNCATE TABLE `administrator`;
--
-- Dumping data for table `administrator`
--

INSERT INTO `administrator` (`employmentID`, `personID`) VALUES
(1, 3),
(2, 4);

-- --------------------------------------------------------

--
-- Table structure for table `courses`
--

CREATE TABLE `courses` (
  `courseID` int(11) NOT NULL,
  `courseCode` varchar(10) NOT NULL,
  `title` varchar(250) NOT NULL,
  `semester` varchar(100) NOT NULL,
  `instructor` varchar(400) NOT NULL,
  `startDate` date NOT NULL,
  `endDate` date NOT NULL
) ;

--
-- RELATIONSHIPS FOR TABLE `courses`:
--

--
-- Truncate table before insert `courses`
--

TRUNCATE TABLE `courses`;
--
-- Dumping data for table `courses`
--

INSERT INTO `courses` (`courseID`, `courseCode`, `title`, `semester`, `instructor`, `startDate`, `endDate`) VALUES
(1, 'SOEN 387', 'Web Programming', 'FALL 2022', 'Mr. Nice', '2022-09-26', '2022-12-29'),
(2, 'COMP 345', 'Operating Systems', 'WINTER 2023', 'Dr. Doolittle', '2023-01-11', '2023-04-26'),
(3, 'COMP 249', 'Introduction To Programming', 'FALL 2022', 'Mr. Gregory The Bestie', '2022-09-01', '2022-12-23'),
(4, 'COMP 371', 'Computer Graphics', 'WINTER 2023', 'Ms. Danika Happy', '2023-01-11', '2023-04-29'),
(5, 'COMP 471', 'Artificial Intelligence', 'FALL 2022', 'Mr. Vader', '2022-09-20', '2022-12-23'),
(6, 'COMP 685', 'Security', 'FALL 2022', 'Q. Mannings', '2022-09-20', '2022-12-23'),
(7, 'COMP 236', 'Introduction to code analysis', 'WINTER 2023', 'Bruce Banner', '2023-01-13', '2023-05-16'),
(8, 'COMP 254', 'Introduction to server software', 'WINTER 2023', 'The Grinch', '2023-01-13', '2023-05-16'),
(9, 'COMP 432', 'Machine Learning', 'WINTER 2023', 'Mr. Bond', '2023-01-13', '2023-05-16'),
(10, 'COMP 442', 'Compiler Design', 'WINTER 2023', 'Java GCC', '2023-01-13', '2023-05-16'),
(11, 'COMP 451', 'Database Design', 'FALL 2022', 'Steve Jobs', '2022-10-03', '2022-12-23');

-- --------------------------------------------------------

--
-- Table structure for table `coursetimes`
--

CREATE TABLE `coursetimes` (
  `courseTimeID` int(11) NOT NULL,
  `courseID` int(11) NOT NULL,
  `startTime` time NOT NULL,
  `endTime` time NOT NULL,
  `day` varchar(10) NOT NULL,
  `section` varchar(30) NOT NULL,
  `room` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- RELATIONSHIPS FOR TABLE `coursetimes`:
--   `courseID`
--       `courses` -> `courseID`
--

--
-- Truncate table before insert `coursetimes`
--

TRUNCATE TABLE `coursetimes`;
--
-- Dumping data for table `coursetimes`
--

INSERT INTO `coursetimes` (`courseTimeID`, `courseID`, `startTime`, `endTime`, `day`, `section`, `room`) VALUES
(1, 3, '12:30:00', '14:30:00', 'monday', 'FAAA', 'H821'),
(2, 3, '12:30:00', '14:30:00', 'monday', 'FAAA', 'H821'),
(3, 3, '12:30:00', '14:30:00', 'monday', 'FAAA', 'H821'),
(4, 2, '14:30:00', '17:30:00', 'monday', 'FAAA', 'H420'),
(5, 2, '14:30:00', '17:30:00', 'tuesday', 'FBB', 'H420'),
(6, 2, '14:30:00', '17:30:00', 'tuesday', 'FBB', 'H420'),
(7, 4, '17:30:00', '20:15:00', 'tuesday', 'FBB', 'JMSB 3-210'),
(8, 4, '17:30:00', '20:15:00', 'tuesday', 'FBB', 'JMSB 3-210'),
(9, 4, '17:30:00', '20:15:00', 'friday', 'FC', 'JMSB 3-210'),
(10, 1, '08:45:00', '10:45:00', 'friday', 'FC', 'H817'),
(11, 1, '08:45:00', '10:45:00', 'friday', 'FC', 'H817'),
(12, 1, '08:45:00', '10:45:00', 'friday', 'FC', 'H817');

-- --------------------------------------------------------

--
-- Table structure for table `person`
--

CREATE TABLE `person` (
  `personID` int(11) NOT NULL,
  `firstName` varchar(250) NOT NULL,
  `lastName` varchar(250) NOT NULL,
  `password` varchar(100) NOT NULL,
  `address` varchar(300) NOT NULL,
  `email` varchar(250) NOT NULL,
  `phoneNumber` varchar(12) NOT NULL,
  `dateOfBirth` date NOT NULL
) ;

--
-- RELATIONSHIPS FOR TABLE `person`:
--

--
-- Truncate table before insert `person`
--

TRUNCATE TABLE `person`;
--
-- Dumping data for table `person`
--

INSERT INTO `person` (`personID`, `firstName`, `lastName`, `password`, `address`, `email`, `phoneNumber`, `dateOfBirth`) VALUES
(1, 'Luke', 'Skywalker', 'test', '801 Mos Eisley', 'notmyfather@gmail.com', '15145861546', '1988-09-09'),
(2, 'Gerard', 'Lenorman', 'test', '29287 Des Cerisiers', 'noublispas@gmail.com', '15145362236', '1976-05-14'),
(3, 'Rocky', 'Balboa', 'test', '999 Uppercut', 'therealchampion@gmail.com', '162256874541', '1980-01-12'),
(4, 'Freddy', 'Kruger', 'test', '87 Elm Street', 'under_your_bed@gmail.com', '15142853649', '1974-03-25'),
(5, 'Mad', 'Max', 'test', '7634 Wasteland', 'witness.me@gmail.com', '15143337851', '1987-02-14');

-- --------------------------------------------------------

--
-- Table structure for table `registrations`
--

CREATE TABLE `registrations` (
  `registrationID` int(11) NOT NULL,
  `studentID` int(11) NOT NULL,
  `courseID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- RELATIONSHIPS FOR TABLE `registrations`:
--   `courseID`
--       `courses` -> `courseID`
--   `studentID`
--       `student` -> `studentID`
--

--
-- Truncate table before insert `registrations`
--

TRUNCATE TABLE `registrations`;
--
-- Dumping data for table `registrations`
--

INSERT INTO `registrations` (`registrationID`, `studentID`, `courseID`) VALUES
(28, 1, 2),
(27, 1, 4),
(24, 1, 7),
(26, 1, 8),
(30, 1, 9),
(33, 1, 11);

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

CREATE TABLE `student` (
  `studentID` int(11) NOT NULL,
  `personID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- RELATIONSHIPS FOR TABLE `student`:
--   `personID`
--       `person` -> `personID`
--

--
-- Truncate table before insert `student`
--

TRUNCATE TABLE `student`;
--
-- Dumping data for table `student`
--

INSERT INTO `student` (`studentID`, `personID`) VALUES
(2, 1),
(1, 2),
(3, 5);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `administrator`
--
ALTER TABLE `administrator`
  ADD PRIMARY KEY (`employmentID`),
  ADD KEY `administrator_personID_FK` (`personID`);

--
-- Indexes for table `courses`
--
ALTER TABLE `courses`
  ADD PRIMARY KEY (`courseID`);

--
-- Indexes for table `coursetimes`
--
ALTER TABLE `coursetimes`
  ADD PRIMARY KEY (`courseTimeID`),
  ADD KEY `courseTimes_courseID_FK` (`courseID`);

--
-- Indexes for table `person`
--
ALTER TABLE `person`
  ADD PRIMARY KEY (`personID`);

--
-- Indexes for table `registrations`
--
ALTER TABLE `registrations`
  ADD PRIMARY KEY (`registrationID`),
  ADD UNIQUE KEY `registrations_noDuplicate` (`studentID`,`courseID`) USING BTREE,
  ADD KEY `registrations_courseID_FK` (`courseID`);

--
-- Indexes for table `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`studentID`),
  ADD KEY `student_personID_FK` (`personID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `administrator`
--
ALTER TABLE `administrator`
  MODIFY `employmentID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `courses`
--
ALTER TABLE `courses`
  MODIFY `courseID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `coursetimes`
--
ALTER TABLE `coursetimes`
  MODIFY `courseTimeID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `person`
--
ALTER TABLE `person`
  MODIFY `personID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `registrations`
--
ALTER TABLE `registrations`
  MODIFY `registrationID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=34;

--
-- AUTO_INCREMENT for table `student`
--
ALTER TABLE `student`
  MODIFY `studentID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `administrator`
--
ALTER TABLE `administrator`
  ADD CONSTRAINT `administrator_personID_FK` FOREIGN KEY (`personID`) REFERENCES `person` (`personID`) ON DELETE CASCADE;

--
-- Constraints for table `coursetimes`
--
ALTER TABLE `coursetimes`
  ADD CONSTRAINT `courseTimes_courseID_FK` FOREIGN KEY (`courseID`) REFERENCES `courses` (`courseID`) ON DELETE CASCADE;

--
-- Constraints for table `registrations`
--
ALTER TABLE `registrations`
  ADD CONSTRAINT `registrations_courseID_FK` FOREIGN KEY (`courseID`) REFERENCES `courses` (`courseID`) ON DELETE CASCADE,
  ADD CONSTRAINT `registrations_studentID_FK` FOREIGN KEY (`studentID`) REFERENCES `student` (`studentID`) ON DELETE CASCADE;

--
-- Constraints for table `student`
--
ALTER TABLE `student`
  ADD CONSTRAINT `student_personID_FK` FOREIGN KEY (`personID`) REFERENCES `person` (`personID`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
