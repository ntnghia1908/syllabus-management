-- MySQL dump 10.13  Distrib 8.0.26, for Win64 (x86_64)
--
-- Host: localhost    Database: digit-curriculum
-- ------------------------------------------------------
-- Server version	8.0.26

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account` (
  `id` int NOT NULL AUTO_INCREMENT,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `user_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `user_role` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `instructor_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK9nkqtsydmph5nrsd3sn1f4w29` (`instructor_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `asiin_assessment_tool`
--

DROP TABLE IF EXISTS `asiin_assessment_tool`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `asiin_assessment_tool` (
  `clo_id` varchar(50) NOT NULL,
  `course_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `assessment_id` int NOT NULL,
  `percentage` int DEFAULT NULL,
  PRIMARY KEY (`clo_id`,`course_id`,`assessment_id`),
  KEY `FK_assessmentTool_assessment_idx` (`assessment_id`) /*!80000 INVISIBLE */,
  KEY `FK_asiin_assessmentTool_course_idx` (`course_id`),
  CONSTRAINT `FK_asiin_assessmentTool_assessment` FOREIGN KEY (`assessment_id`) REFERENCES `assessment` (`id`),
  CONSTRAINT `FK_asiin_assessmentTool_clo` FOREIGN KEY (`clo_id`) REFERENCES `asiin_clo` (`id`),
  CONSTRAINT `FK_asiin_assessmentTool_course` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `asiin_clo`
--

DROP TABLE IF EXISTS `asiin_clo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `asiin_clo` (
  `id` varchar(50) NOT NULL,
  `description` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `asiin_closlo`
--

DROP TABLE IF EXISTS `asiin_closlo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `asiin_closlo` (
  `clo_id` varchar(50) NOT NULL,
  `slo_id` int unsigned NOT NULL,
  `percentage` int DEFAULT NULL,
  `level` int DEFAULT NULL,
  PRIMARY KEY (`clo_id`,`slo_id`),
  KEY `slo_closlo_asiin_idx` (`slo_id`),
  CONSTRAINT `FK_closlo_clo` FOREIGN KEY (`clo_id`) REFERENCES `asiin_clo` (`id`),
  CONSTRAINT `FK_closlo_slo` FOREIGN KEY (`slo_id`) REFERENCES `slo` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `assessment`
--

DROP TABLE IF EXISTS `assessment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `assessment` (
  `id` int NOT NULL,
  `type` varchar(255) DEFAULT NULL,
  `type_vn` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `assessment_tool`
--

DROP TABLE IF EXISTS `assessment_tool`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `assessment_tool` (
  `assessment_id` int NOT NULL,
  `course_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `loutcome_id` int NOT NULL,
  `precentage` int DEFAULT NULL,
  UNIQUE KEY `assessment_tool_id` (`course_id`,`assessment_id`,`loutcome_id`) USING BTREE,
  KEY `FK_AssessmentTool_LOutcome` (`loutcome_id`),
  KEY `FK_AssessmentTool_AssessmentCourse` (`assessment_id`,`course_id`),
  CONSTRAINT `FK_AssessmentTool_Assessment` FOREIGN KEY (`assessment_id`) REFERENCES `assessment` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_AssessmentTool_Course` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_AssessmentTool_LOutcome` FOREIGN KEY (`loutcome_id`) REFERENCES `learning_outcome` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `book`
--

DROP TABLE IF EXISTS `book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `book` (
  `id` int NOT NULL,
  `author` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `version` varchar(255) DEFAULT NULL,
  `publisher` varchar(255) DEFAULT NULL,
  `year` int DEFAULT NULL,
  `ISBN` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `class`
--

DROP TABLE IF EXISTS `class`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `class` (
  `id` int NOT NULL AUTO_INCREMENT,
  `course_id` varchar(50) DEFAULT '0',
  `group_theory` int DEFAULT '0',
  `group_lab` int DEFAULT '0',
  `num_students` int DEFAULT '0',
  `instructor_id` varchar(50) DEFAULT '0',
  `semester` int DEFAULT '0',
  `academic_year` varchar(50) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `course_id_group_theory_group_lab_semester_academic_year` (`course_id`,`group_theory`,`group_lab`,`semester`,`academic_year`)
) ENGINE=InnoDB AUTO_INCREMENT=561 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `class_assessment`
--

DROP TABLE IF EXISTS `class_assessment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `class_assessment` (
  `assessment_id` int NOT NULL,
  `class_id` varchar(50) NOT NULL,
  `learning_outcome_id` int NOT NULL,
  `precentage` float DEFAULT NULL,
  PRIMARY KEY (`assessment_id`,`class_id`,`learning_outcome_id`),
  KEY `FKmevtb7furfoqcnjqn5wuafdx5` (`class_id`),
  KEY `FKgvbc7qhba0d7odssq6rtompih` (`learning_outcome_id`),
  CONSTRAINT `classAssessement_LearingOutcome` FOREIGN KEY (`learning_outcome_id`) REFERENCES `learning_outcome` (`id`),
  CONSTRAINT `classAssessmenet_ClassSession` FOREIGN KEY (`class_id`) REFERENCES `class_session` (`id`),
  CONSTRAINT `FKcdrm0f8q2dbqmsrwi90lcjthx` FOREIGN KEY (`assessment_id`) REFERENCES `assessment` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `class_assessment_course`
--

DROP TABLE IF EXISTS `class_assessment_course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `class_assessment_course` (
  `class_id` varchar(50) NOT NULL,
  `assessment_id` int NOT NULL,
  `percentage` int DEFAULT NULL,
  PRIMARY KEY (`class_id`,`assessment_id`),
  KEY `fk_class_assessment_course_assessment` (`assessment_id`) USING BTREE,
  KEY `fk_class_assessment_course_class` (`class_id`) USING BTREE,
  CONSTRAINT `classAssessmentCourse_assessement` FOREIGN KEY (`assessment_id`) REFERENCES `assessment` (`id`),
  CONSTRAINT `classAssessmentCourse_classSession` FOREIGN KEY (`class_id`) REFERENCES `class_session` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `class_session`
--

DROP TABLE IF EXISTS `class_session`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `class_session` (
  `id` varchar(50) NOT NULL,
  `course_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `semester` int DEFAULT NULL,
  `academic_year` varchar(255) DEFAULT NULL,
  `group_theory` int DEFAULT NULL,
  `instructor_id` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_classSession_course` (`course_id`),
  KEY `FK_classSession_instructor_idx` (`instructor_id`),
  CONSTRAINT `FK_classSession_course` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `class_slo_clo`
--

DROP TABLE IF EXISTS `class_slo_clo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `class_slo_clo` (
  `class_id` varchar(50) NOT NULL,
  `clo_id` int NOT NULL,
  `slo_id` int unsigned NOT NULL,
  `percentage` float DEFAULT NULL,
  PRIMARY KEY (`class_id`,`clo_id`,`slo_id`),
  KEY `FKecnfqj0x90g694r9lpkch9v65` (`clo_id`),
  KEY `FKpfxxfwt6crqhsnjdhn8ltf4cu` (`slo_id`),
  CONSTRAINT `classSLOCLO_classSession` FOREIGN KEY (`class_id`) REFERENCES `class_session` (`id`),
  CONSTRAINT `classSLOCLO_clo` FOREIGN KEY (`clo_id`) REFERENCES `learning_outcome` (`id`),
  CONSTRAINT `classSLOCLO_slo` FOREIGN KEY (`slo_id`) REFERENCES `slo` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `clo_asiin`
--

DROP TABLE IF EXISTS `clo_asiin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clo_asiin` (
  `id` varchar(50) NOT NULL,
  `description` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `clo_slo`
--

DROP TABLE IF EXISTS `clo_slo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clo_slo` (
  `slo_id` int unsigned NOT NULL,
  `lo_id` int NOT NULL,
  `percentage` int DEFAULT NULL,
  `level` int DEFAULT NULL,
  KEY `fk_clo_slo_learning_outcome` (`lo_id`),
  KEY `fk_clo_slo_slo` (`slo_id`),
  CONSTRAINT `fk_clo_slo_learning_outcome` FOREIGN KEY (`lo_id`) REFERENCES `learning_outcome` (`id`),
  CONSTRAINT `fk_clo_slo_slo` FOREIGN KEY (`slo_id`) REFERENCES `slo` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `course`
--

DROP TABLE IF EXISTS `course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course` (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `course_level_id` int NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `name_vn` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `credit_theory` varchar(255) DEFAULT NULL,
  `credit_lab` varchar(255) DEFAULT NULL,
  `description` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Course_CourseLevel` (`course_level_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `course_assessment`
--

DROP TABLE IF EXISTS `course_assessment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course_assessment` (
  `assessment_id` int NOT NULL,
  `course_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `percentage` int NOT NULL,
  PRIMARY KEY (`assessment_id`,`course_id`),
  KEY `Fk_AssessmentCourse_Course` (`course_id`),
  CONSTRAINT `Fk_AssessmentCourse_Course` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `course_assessment_asiin`
--

DROP TABLE IF EXISTS `course_assessment_asiin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course_assessment_asiin` (
  `assessment_id` int NOT NULL,
  `course_id` varchar(255) NOT NULL,
  `percentage` int DEFAULT NULL,
  PRIMARY KEY (`assessment_id`,`course_id`),
  KEY `assessment_course_idx` (`course_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `course_book`
--

DROP TABLE IF EXISTS `course_book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course_book` (
  `book_id` int NOT NULL,
  `course_id` varchar(255) NOT NULL,
  `type` varchar(255) DEFAULT NULL,
  UNIQUE KEY `Key` (`book_id`,`course_id`),
  KEY `FK_BookCourse_Course` (`course_id`),
  CONSTRAINT `FK_BookCourse_Book` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_BookCourse_Course` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `course_course_relationship`
--

DROP TABLE IF EXISTS `course_course_relationship`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course_course_relationship` (
  `course_id1` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `course_id2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `relationship_id` int NOT NULL,
  UNIQUE KEY `Key` (`course_id1`,`course_id2`),
  KEY `FKCourse_Course2` (`course_id2`),
  KEY `CourseCourse_CourseRelationship` (`relationship_id`),
  CONSTRAINT `CourseCourse_CourseRelationship` FOREIGN KEY (`relationship_id`) REFERENCES `course_relationship` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FKCourse_Course1` FOREIGN KEY (`course_id1`) REFERENCES `course` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FKCourse_Course2` FOREIGN KEY (`course_id2`) REFERENCES `course` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `course_department`
--

DROP TABLE IF EXISTS `course_department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course_department` (
  `course_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `department_id` int NOT NULL,
  UNIQUE KEY `coruse_id` (`course_id`,`department_id`),
  KEY `FK_CourseDepartment_Department` (`department_id`),
  CONSTRAINT `FK_CourseDepartment_Coursre` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_CourseDepartment_Department` FOREIGN KEY (`department_id`) REFERENCES `department` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Temporary view structure for view `course_in_which_semester_ds21`
--

DROP TABLE IF EXISTS `course_in_which_semester_ds21`;
/*!50001 DROP VIEW IF EXISTS `course_in_which_semester_ds21`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `course_in_which_semester_ds21` AS SELECT 
 1 AS `course_id`,
 1 AS `Semester`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `course_instructor`
--

DROP TABLE IF EXISTS `course_instructor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course_instructor` (
  `course_id` varchar(255) NOT NULL,
  `instructor_id` int NOT NULL,
  UNIQUE KEY `course_id` (`course_id`,`instructor_id`),
  KEY `FK_CourseInstructor_Instructor` (`instructor_id`),
  CONSTRAINT `FK_CourseInstructor_Course` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `course_level`
--

DROP TABLE IF EXISTS `course_level`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course_level` (
  `id` int NOT NULL,
  `level` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_course_level_course` FOREIGN KEY (`id`) REFERENCES `course` (`course_level_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Temporary view structure for view `course_list_cs_19`
--

DROP TABLE IF EXISTS `course_list_cs_19`;
/*!50001 DROP VIEW IF EXISTS `course_list_cs_19`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `course_list_cs_19` AS SELECT 
 1 AS `course_id`,
 1 AS `course_name`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `course_list_ds`
--

DROP TABLE IF EXISTS `course_list_ds`;
/*!50001 DROP VIEW IF EXISTS `course_list_ds`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `course_list_ds` AS SELECT 
 1 AS `course_id`,
 1 AS `course_name`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `course_pathway`
--

DROP TABLE IF EXISTS `course_pathway`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course_pathway` (
  `program_id` int NOT NULL,
  `pathway_id` int NOT NULL,
  `course_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `semester` int NOT NULL,
  `year` int NOT NULL,
  UNIQUE KEY `program_id` (`program_id`,`pathway_id`,`course_id`),
  KEY `FK_CoursePathway_Course` (`course_id`),
  KEY `FK_CoursePathway_Pathway` (`pathway_id`),
  CONSTRAINT `FK_CoursePathway_Course` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_CoursePathway_Pathway` FOREIGN KEY (`pathway_id`) REFERENCES `pathway` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_CoursePathway_Program` FOREIGN KEY (`program_id`) REFERENCES `program` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `course_program`
--

DROP TABLE IF EXISTS `course_program`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course_program` (
  `course_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `program_id` int NOT NULL,
  `course_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `course_type_id` int NOT NULL,
  UNIQUE KEY `Key` (`course_id`,`program_id`),
  KEY `FK_CourseProgram_CourseType` (`course_type_id`),
  KEY `FK_CourseProgram_Program` (`program_id`),
  CONSTRAINT `FK_CourseProgram_Course` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_CourseProgram_CourseType` FOREIGN KEY (`course_type_id`) REFERENCES `course_type` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_CourseProgram_Program` FOREIGN KEY (`program_id`) REFERENCES `program` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `course_relationship`
--

DROP TABLE IF EXISTS `course_relationship`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course_relationship` (
  `id` int NOT NULL,
  `relationship` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `course_type`
--

DROP TABLE IF EXISTS `course_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course_type` (
  `id` int NOT NULL,
  `type` varchar(255) DEFAULT NULL,
  `type_vn` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Temporary view structure for view `course_type_ds_19_20`
--

DROP TABLE IF EXISTS `course_type_ds_19_20`;
/*!50001 DROP VIEW IF EXISTS `course_type_ds_19_20`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `course_type_ds_19_20` AS SELECT 
 1 AS `course_id`,
 1 AS `type`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `curriculum_4years_template`
--

DROP TABLE IF EXISTS `curriculum_4years_template`;
/*!50001 DROP VIEW IF EXISTS `curriculum_4years_template`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `curriculum_4years_template` AS SELECT 
 1 AS `year`,
 1 AS `semester`,
 1 AS `course_code`,
 1 AS `Course Name`,
 1 AS `Credit`,
 1 AS `Program`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `curriculum_8semesters_template`
--

DROP TABLE IF EXISTS `curriculum_8semesters_template`;
/*!50001 DROP VIEW IF EXISTS `curriculum_8semesters_template`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `curriculum_8semesters_template` AS SELECT 
 1 AS `Semester`,
 1 AS `course_code`,
 1 AS `Course Name`,
 1 AS `Credit`,
 1 AS `Program`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `curriculum_cs_4year_it_subject_only`
--

DROP TABLE IF EXISTS `curriculum_cs_4year_it_subject_only`;
/*!50001 DROP VIEW IF EXISTS `curriculum_cs_4year_it_subject_only`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `curriculum_cs_4year_it_subject_only` AS SELECT 
 1 AS `course_code`,
 1 AS `Course Name`,
 1 AS `credit_theory`,
 1 AS `credit_lab`,
 1 AS `Program`,
 1 AS `year`,
 1 AS `semester`,
 1 AS `degree`,
 1 AS `name`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `curriculum_cs_ae1_4years`
--

DROP TABLE IF EXISTS `curriculum_cs_ae1_4years`;
/*!50001 DROP VIEW IF EXISTS `curriculum_cs_ae1_4years`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `curriculum_cs_ae1_4years` AS SELECT 
 1 AS `course_code`,
 1 AS `Course Name`,
 1 AS `year`,
 1 AS `semester`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `curriculum_cs_ae1_8semesters`
--

DROP TABLE IF EXISTS `curriculum_cs_ae1_8semesters`;
/*!50001 DROP VIEW IF EXISTS `curriculum_cs_ae1_8semesters`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `curriculum_cs_ae1_8semesters` AS SELECT 
 1 AS `Semester`,
 1 AS `course_code`,
 1 AS `Course Name`,
 1 AS `Credits`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `department`
--

DROP TABLE IF EXISTS `department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `department` (
  `id` int NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `department_instructor`
--

DROP TABLE IF EXISTS `department_instructor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `department_instructor` (
  `department_id` int NOT NULL,
  `instructor_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  UNIQUE KEY `department_instructor_key` (`department_id`,`instructor_id`),
  KEY `FK_DepartmentInstructor_Instructor` (`instructor_id`),
  CONSTRAINT `FK_department_instructor_instructor` FOREIGN KEY (`instructor_id`) REFERENCES `instructor` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_DepartmentInstructor_Department` FOREIGN KEY (`department_id`) REFERENCES `department` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `discipline`
--

DROP TABLE IF EXISTS `discipline`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `discipline` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `documentary`
--

DROP TABLE IF EXISTS `documentary`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `documentary` (
  `document_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `id` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  UNIQUE KEY `document_id_UNIQUE` (`document_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `instructor`
--

DROP TABLE IF EXISTS `instructor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `instructor` (
  `id` varchar(50) NOT NULL DEFAULT '',
  `name` varchar(255) DEFAULT NULL,
  `degree` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `learning_outcome`
--

DROP TABLE IF EXISTS `learning_outcome`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `learning_outcome` (
  `id` int NOT NULL,
  `course_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `description` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `description_vn` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_LearningOutcome` (`course_id`),
  CONSTRAINT `FK_LearningOutcome` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Temporary view structure for view `lecturer_with_course`
--

DROP TABLE IF EXISTS `lecturer_with_course`;
/*!50001 DROP VIEW IF EXISTS `lecturer_with_course`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `lecturer_with_course` AS SELECT 
 1 AS `course_id`,
 1 AS `Lecturer`,
 1 AS `email`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `major`
--

DROP TABLE IF EXISTS `major`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `major` (
  `id` int NOT NULL AUTO_INCREMENT,
  `discipline_id` int DEFAULT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `short_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_major_discipline` (`discipline_id`),
  CONSTRAINT `FK_major_discipline` FOREIGN KEY (`discipline_id`) REFERENCES `discipline` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `pathway`
--

DROP TABLE IF EXISTS `pathway`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pathway` (
  `id` int NOT NULL,
  `pathway` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `program`
--

DROP TABLE IF EXISTS `program`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `program` (
  `id` int NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `duration` varchar(255) DEFAULT NULL,
  `version` varchar(4) DEFAULT NULL,
  `major_id` int DEFAULT NULL,
  `program_type_id` int NOT NULL,
  `valid_from` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_program_major` (`major_id`),
  KEY `fk_program_program_type` (`program_type_id`) USING BTREE,
  CONSTRAINT `FK_program_major` FOREIGN KEY (`major_id`) REFERENCES `major` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_program_program_type` FOREIGN KEY (`program_type_id`) REFERENCES `program_type` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `program_document`
--

DROP TABLE IF EXISTS `program_document`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `program_document` (
  `program_id` int DEFAULT NULL,
  `document_id` int DEFAULT NULL,
  `id` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  KEY `FK1_idx` (`program_id`),
  KEY `FK2_idx` (`document_id`),
  CONSTRAINT `FK1` FOREIGN KEY (`program_id`) REFERENCES `program` (`id`),
  CONSTRAINT `FK2` FOREIGN KEY (`document_id`) REFERENCES `documentary` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `program_type`
--

DROP TABLE IF EXISTS `program_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `program_type` (
  `id` int NOT NULL AUTO_INCREMENT,
  `type` varchar(255) NOT NULL,
  `description` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Temporary view structure for view `reading_list`
--

DROP TABLE IF EXISTS `reading_list`;
/*!50001 DROP VIEW IF EXISTS `reading_list`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `reading_list` AS SELECT 
 1 AS `course_id`,
 1 AS `books`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `result`
--

DROP TABLE IF EXISTS `result`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `result` (
  `student_id` varchar(50) NOT NULL,
  `class_id` varchar(255) NOT NULL,
  `mid_score` int DEFAULT NULL,
  `final_score` int DEFAULT NULL,
  `in_class_score` int DEFAULT NULL,
  `GPA` int DEFAULT NULL,
  `abet_score` int DEFAULT NULL,
  `abet_1` int DEFAULT NULL,
  `abet_2` int DEFAULT NULL,
  `abet_3` int DEFAULT NULL,
  `abet_4` int DEFAULT NULL,
  `abet_5` int DEFAULT NULL,
  `abet_6` int DEFAULT NULL,
  `avg` float DEFAULT NULL,
  PRIMARY KEY (`student_id`,`class_id`),
  KEY `FK_Result_ClassSession_idx` (`class_id`),
  CONSTRAINT `FK_Result_ClassSession` FOREIGN KEY (`class_id`) REFERENCES `class_session` (`id`),
  CONSTRAINT `FK_Result_Student` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `results_temp`
--

DROP TABLE IF EXISTS `results_temp`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `results_temp` (
  `class_id` text,
  `course_id` text,
  `group_id` int DEFAULT NULL,
  `sem` int DEFAULT NULL,
  `year` text,
  `student_id` text,
  `assignment` int DEFAULT NULL,
  `mid` int DEFAULT NULL,
  `final` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `slo`
--

DROP TABLE IF EXISTS `slo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `slo` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `criteria` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student` (
  `id` varchar(50) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `major` varchar(45) DEFAULT NULL,
  `batch` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `student_course`
--

DROP TABLE IF EXISTS `student_course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student_course` (
  `StudentID` bigint NOT NULL,
  `CourseID` bigint NOT NULL,
  PRIMARY KEY (`StudentID`,`CourseID`),
  KEY `StudentCourse_StudentID_INDEX` (`StudentID`),
  KEY `StudentCourse_CourseID_INDEX` (`CourseID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Temporary view structure for view `syllabus_generater`
--

DROP TABLE IF EXISTS `syllabus_generater`;
/*!50001 DROP VIEW IF EXISTS `syllabus_generater`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `syllabus_generater` AS SELECT 
 1 AS `CourseID`,
 1 AS `Vietnamese`,
 1 AS `English`,
 1 AS `Lecturer`,
 1 AS `Laborator`,
 1 AS `totalCredit`,
 1 AS `description`,
 1 AS `Course Type`,
 1 AS `Intructor Name`,
 1 AS `Department`,
 1 AS `Email`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `teaching_activity`
--

DROP TABLE IF EXISTS `teaching_activity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `teaching_activity` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  `description` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Temporary view structure for view `teaching_methods_ds21`
--

DROP TABLE IF EXISTS `teaching_methods_ds21`;
/*!50001 DROP VIEW IF EXISTS `teaching_methods_ds21`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `teaching_methods_ds21` AS SELECT 
 1 AS `course_id`,
 1 AS `teaching_methods`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `topic`
--

DROP TABLE IF EXISTS `topic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `topic` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(510) DEFAULT NULL,
  `topic_type_id` int NOT NULL,
  `course_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Topic_Course` (`course_id`),
  KEY `FK_topic_topic_type` (`topic_type_id`),
  CONSTRAINT `fk_topic_course` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=942 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `topic_assessment`
--

DROP TABLE IF EXISTS `topic_assessment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `topic_assessment` (
  `topic_id` int DEFAULT NULL,
  `assessment_id` int DEFAULT NULL,
  KEY `fk_topic_idx` (`topic_id`),
  KEY `fk_topic_assessment_assessment_idx` (`assessment_id`),
  CONSTRAINT `fk_topic` FOREIGN KEY (`topic_id`) REFERENCES `topic` (`id`),
  CONSTRAINT `fk_topic_assessment_assessment` FOREIGN KEY (`assessment_id`) REFERENCES `assessment` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `topic_book`
--

DROP TABLE IF EXISTS `topic_book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `topic_book` (
  `topic_id` int DEFAULT NULL,
  `book_id` int DEFAULT NULL,
  KEY `fk_topicBook_topic_idx` (`topic_id`),
  KEY `fk_topicBook_book_idx` (`book_id`),
  CONSTRAINT `fk_topicBook_book` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`),
  CONSTRAINT `fk_topicBook_topic` FOREIGN KEY (`topic_id`) REFERENCES `topic` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `topic_detail`
--

DROP TABLE IF EXISTS `topic_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `topic_detail` (
  `id` int NOT NULL,
  `topic_id` int NOT NULL,
  `week` int DEFAULT NULL,
  `topic_detail` varchar(1000) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_topic_TopicDetail` (`topic_id`),
  CONSTRAINT `FK_topic_TopicDetail` FOREIGN KEY (`topic_id`) REFERENCES `topic` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `topic_teaching_activity`
--

DROP TABLE IF EXISTS `topic_teaching_activity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `topic_teaching_activity` (
  `teaching_activity_id` int DEFAULT NULL,
  `topic_id` int DEFAULT NULL,
  KEY `fk_teaching_activity_topic_topic_idx` (`topic_id`),
  KEY `fk_teaching_activity_teaching_activity_idx` (`teaching_activity_id`),
  CONSTRAINT `fk_teaching_activity_teaching_activity` FOREIGN KEY (`teaching_activity_id`) REFERENCES `teaching_activity` (`id`),
  CONSTRAINT `fk_teaching_activity_topic_topic` FOREIGN KEY (`topic_id`) REFERENCES `topic` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `topic_type`
--

DROP TABLE IF EXISTS `topic_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `topic_type` (
  `id` int NOT NULL,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FKTopic_TopicType` FOREIGN KEY (`id`) REFERENCES `topic` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Temporary view structure for view `total_credits_by_semester_cs`
--

DROP TABLE IF EXISTS `total_credits_by_semester_cs`;
/*!50001 DROP VIEW IF EXISTS `total_credits_by_semester_cs`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `total_credits_by_semester_cs` AS SELECT 
 1 AS `Semester`,
 1 AS `Credits`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `total_credits_by_semester_ds`
--

DROP TABLE IF EXISTS `total_credits_by_semester_ds`;
/*!50001 DROP VIEW IF EXISTS `total_credits_by_semester_ds`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `total_credits_by_semester_ds` AS SELECT 
 1 AS `Semester`,
 1 AS `Credits`*/;
SET character_set_client = @saved_cs_client;

--
-- Final view structure for view `course_in_which_semester_ds21`
--

/*!50001 DROP VIEW IF EXISTS `course_in_which_semester_ds21`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `course_in_which_semester_ds21` AS select `plan`.`course_id` AS `course_id`,group_concat(distinct (((`plan`.`year` - 1) * 2) + `plan`.`semester`) separator ',') AS `Semester` from `course_pathway` `plan` where (`plan`.`program_id` = 1) group by `plan`.`course_id` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `course_list_cs_19`
--

/*!50001 DROP VIEW IF EXISTS `course_list_cs_19`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `course_list_cs_19` AS select distinct `cp`.`course_id` AS `course_id`,`c`.`name` AS `course_name` from (`course_program` `cp` join `course` `c` on((`cp`.`course_id` = `c`.`id`))) where (`cp`.`program_id` = 4) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `course_list_ds`
--

/*!50001 DROP VIEW IF EXISTS `course_list_ds`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `course_list_ds` AS select distinct `cp`.`course_id` AS `course_id`,`c`.`name` AS `course_name` from (`course_program` `cp` join `course` `c` on((`cp`.`course_id` = `c`.`id`))) where (`cp`.`program_id` = 1) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `course_type_ds_19_20`
--

/*!50001 DROP VIEW IF EXISTS `course_type_ds_19_20`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `course_type_ds_19_20` AS select `cp`.`course_id` AS `course_id`,`ct`.`type` AS `type` from (`course_program` `cp` join `course_type` `ct` on((`ct`.`id` = `cp`.`course_type_id`))) where (`cp`.`program_id` = 1) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `curriculum_4years_template`
--

/*!50001 DROP VIEW IF EXISTS `curriculum_4years_template`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=TEMPTABLE */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `curriculum_4years_template` AS select `plan`.`year` AS `year`,`plan`.`semester` AS `semester`,`cp`.`course_code` AS `course_code`,`c`.`name` AS `Course Name`,concat((`c`.`credit_theory` + `c`.`credit_lab`),' (',`c`.`credit_theory`,', ',`c`.`credit_lab`,')') AS `Credit`,concat(`p`.`name`,`p`.`version`) AS `Program` from (((`course` `c` join `course_pathway` `plan` on((`plan`.`course_id` = `c`.`id`))) join `program` `p` on((`plan`.`program_id` = `p`.`id`))) join `course_program` `cp` on(((`plan`.`program_id` = `cp`.`program_id`) and (`cp`.`course_id` = `plan`.`course_id`)))) where ((`plan`.`pathway_id` = 3) and (`plan`.`program_id` = 1)) order by `plan`.`year`,`plan`.`semester`,`cp`.`course_code` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `curriculum_8semesters_template`
--

/*!50001 DROP VIEW IF EXISTS `curriculum_8semesters_template`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=TEMPTABLE */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `curriculum_8semesters_template` AS select (((`plan`.`year` - 1) * 2) + `plan`.`semester`) AS `Semester`,`cp`.`course_code` AS `course_code`,`c`.`name` AS `Course Name`,concat((`c`.`credit_theory` + `c`.`credit_lab`),' (',`c`.`credit_theory`,', ',`c`.`credit_lab`,')') AS `Credit`,concat(`p`.`name`,`p`.`version`) AS `Program` from (((`course_pathway` `plan` join `course` `c` on((`plan`.`course_id` = `c`.`id`))) join `program` `p` on((`plan`.`program_id` = `p`.`id`))) join `course_program` `cp` on(((`plan`.`program_id` = `cp`.`program_id`) and (`cp`.`course_id` = `plan`.`course_id`)))) where ((`plan`.`pathway_id` = 1) and (`plan`.`program_id` = 4)) order by `plan`.`year`,`plan`.`semester`,`cp`.`course_code` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `curriculum_cs_4year_it_subject_only`
--

/*!50001 DROP VIEW IF EXISTS `curriculum_cs_4year_it_subject_only`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=TEMPTABLE */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `curriculum_cs_4year_it_subject_only` AS select `cp`.`course_code` AS `course_code`,`c`.`name` AS `Course Name`,`c`.`credit_theory` AS `credit_theory`,`c`.`credit_lab` AS `credit_lab`,`p`.`name` AS `Program`,`plan`.`year` AS `year`,`plan`.`semester` AS `semester`,`i`.`degree` AS `degree`,`i`.`name` AS `name` from ((((((`course_pathway` `plan` join `course` `c` on((`plan`.`course_id` = `c`.`id`))) join `course_department` `cd` on((`cd`.`course_id` = `c`.`id`))) join `course_instructor` `ci` on((`ci`.`course_id` = `c`.`id`))) join `instructor` `i` on((`i`.`id` = `ci`.`instructor_id`))) join `program` `p` on((`plan`.`program_id` = `p`.`id`))) join `course_program` `cp` on(((`plan`.`program_id` = `cp`.`program_id`) and (`plan`.`course_id` = `cp`.`course_id`)))) where ((`plan`.`pathway_id` = 3) and (`plan`.`program_id` = 4) and (`cd`.`department_id` = 1)) order by `plan`.`year`,`plan`.`semester`,`cp`.`course_code` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `curriculum_cs_ae1_4years`
--

/*!50001 DROP VIEW IF EXISTS `curriculum_cs_ae1_4years`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=TEMPTABLE */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `curriculum_cs_ae1_4years` AS select `cp`.`course_code` AS `course_code`,`c`.`name` AS `Course Name`,`plan`.`year` AS `year`,`plan`.`semester` AS `semester` from (((`course_pathway` `plan` join `course` `c` on((`plan`.`course_id` = `c`.`id`))) join `program` `p` on((`plan`.`program_id` = `p`.`id`))) join `course_program` `cp` on(((`plan`.`program_id` = `cp`.`program_id`) and (`cp`.`course_id` = `plan`.`course_id`)))) where ((`plan`.`pathway_id` = 3) and (`plan`.`program_id` = 4)) order by `plan`.`year`,`plan`.`semester`,`cp`.`course_code` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `curriculum_cs_ae1_8semesters`
--

/*!50001 DROP VIEW IF EXISTS `curriculum_cs_ae1_8semesters`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=TEMPTABLE */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `curriculum_cs_ae1_8semesters` AS select (((`plan`.`year` - 1) * 2) + `plan`.`semester`) AS `Semester`,`cp`.`course_code` AS `course_code`,`c`.`name` AS `Course Name`,(`c`.`credit_theory` + `c`.`credit_lab`) AS `Credits` from (((`course_pathway` `plan` join `course` `c` on((`plan`.`course_id` = `c`.`id`))) join `program` `p` on((`plan`.`program_id` = `p`.`id`))) join `course_program` `cp` on(((`plan`.`program_id` = `cp`.`program_id`) and (`cp`.`course_id` = `plan`.`course_id`)))) where ((`plan`.`pathway_id` = 3) and (`plan`.`program_id` = 48)) order by `plan`.`year`,`plan`.`semester`,`cp`.`course_code` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `lecturer_with_course`
--

/*!50001 DROP VIEW IF EXISTS `lecturer_with_course`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `lecturer_with_course` AS select `ci`.`course_id` AS `course_id`,concat(`i`.`name`,', ',`i`.`degree`) AS `Lecturer`,`i`.`email` AS `email` from (`course_instructor` `ci` left join `instructor` `i` on((`ci`.`instructor_id` = `i`.`id`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `reading_list`
--

/*!50001 DROP VIEW IF EXISTS `reading_list`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `reading_list` AS select `cb`.`course_id` AS `course_id`,group_concat(concat_ws('',`b`.`author`,',',`b`.`title`,',',`b`.`version`,',',`b`.`publisher`,',',`b`.`year`) separator '|') AS `books` from (`course_book` `cb` join `book` `b` on((`cb`.`book_id` = `b`.`id`))) group by `cb`.`course_id` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `syllabus_generater`
--

/*!50001 DROP VIEW IF EXISTS `syllabus_generater`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=TEMPTABLE */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `syllabus_generater` AS select distinct `c`.`id` AS `CourseID`,`c`.`name_vn` AS `Vietnamese`,`c`.`name` AS `English`,`c`.`credit_theory` AS `Lecturer`,`c`.`credit_lab` AS `Laborator`,(`c`.`credit_theory` + `c`.`credit_lab`) AS `totalCredit`,`c`.`description` AS `description`,`ct`.`type` AS `Course Type`,concat(`i`.`name`,', ',`i`.`degree`) AS `Intructor Name`,`d`.`name` AS `Department`,`i`.`email` AS `Email` from ((((((`course` `c` join `course_program` `cp` on((`c`.`id` = `cp`.`course_id`))) join `course_type` `ct` on((`cp`.`course_type_id` = `ct`.`id`))) join `course_department` `cd` on((`cd`.`course_id` = `c`.`id`))) join `department` `d` on((`cd`.`department_id` = `d`.`id`))) join `course_instructor` `ci` on((`ci`.`course_id` = `c`.`id`))) join `instructor` `i` on((`ci`.`instructor_id` = `i`.`id`))) where (`d`.`id` = 1) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `teaching_methods_ds21`
--

/*!50001 DROP VIEW IF EXISTS `teaching_methods_ds21`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `teaching_methods_ds21` AS select `ca`.`course_id` AS `course_id`,group_concat(`ass`.`type` separator ',') AS `teaching_methods` from (`course_assessment_asiin` `ca` join `assessment` `ass` on((`ass`.`id` = `ca`.`assessment_id`))) where (`ass`.`id` not in (6,4)) group by `ca`.`course_id` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `total_credits_by_semester_cs`
--

/*!50001 DROP VIEW IF EXISTS `total_credits_by_semester_cs`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=TEMPTABLE */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `total_credits_by_semester_cs` AS select (((`plan`.`year` - 1) * 2) + `plan`.`semester`) AS `Semester`,sum((`c`.`credit_theory` + `c`.`credit_lab`)) AS `Credits` from (((`course_pathway` `plan` join `course` `c` on((`plan`.`course_id` = `c`.`id`))) join `program` `p` on((`plan`.`program_id` = `p`.`id`))) join `course_program` `cp` on(((`plan`.`program_id` = `cp`.`program_id`) and (`cp`.`course_id` = `plan`.`course_id`)))) where ((`plan`.`pathway_id` = 3) and (`plan`.`program_id` = 4)) group by (((`plan`.`year` - 1) * 2) + `plan`.`semester`) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `total_credits_by_semester_ds`
--

/*!50001 DROP VIEW IF EXISTS `total_credits_by_semester_ds`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=TEMPTABLE */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `total_credits_by_semester_ds` AS select (((`plan`.`year` - 1) * 2) + `plan`.`semester`) AS `Semester`,sum((`c`.`credit_theory` + `c`.`credit_lab`)) AS `Credits` from (((`course_pathway` `plan` join `course` `c` on((`plan`.`course_id` = `c`.`id`))) join `program` `p` on((`plan`.`program_id` = `p`.`id`))) join `course_program` `cp` on(((`plan`.`program_id` = `cp`.`program_id`) and (`cp`.`course_id` = `plan`.`course_id`)))) where ((`plan`.`pathway_id` = 3) and (`plan`.`program_id` = 1)) group by (((`plan`.`year` - 1) * 2) + `plan`.`semester`) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-03-27 22:53:56
