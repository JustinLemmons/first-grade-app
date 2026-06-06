CREATE DATABASE IF NOT EXISTS first_grade_db;
USE first_grade_db;

-- Tables --

CREATE TABLE teachers (
	id		BIGINT AUTO_INCREMENT PRIMARY KEY,
	first_name 	VARCHAR(100) NOT NULL,
	last_name	VARCHAR(100) NOT NULL,
	email 		VARCHAR(255) NOT NULL UNIQUE,
	created_at	TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE students (
	id		BIGINT AUTO_INCREMENT PRIMARY KEY,
	first_name	VARCHAR(100) NOT NULL,
	last_name	VARCHAR(100) NOT NULL,
	email		VARCHAR(255) NOT NULL UNIQUE,
	teacher_id	BIGINT NOT NULL,
	created_at	TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	CONSTRAINT 	fk_student_teacher FOREIGN KEY (teacher_id) REFERENCES teachers(id)
);

CREATE TABLE assignments (
	id		BIGINT AUTO-INCREMENT PRIMARY KEY,
	title		VARCHAR(255) NOT NULL,
	description	TEXT,
	subject		ENUM('MATH', 'WRITING') NOT NULL,
	due_date	DATE NOT NULL,
	teacher_id	BIGINT NOT NULL,
	created_at	TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	CONSTRAINT fk_assignment_teacher FOREIGN KEY (teacher_id) REFERENCES teachers(id)
);

CREATE TABLE student_assignments (
	id		BIGINT AUTO-INCREMENT PRIMARY KEY,
	student_id	BIGINT NOT NULL,
	assignment_id	BIGINT NOT NULL,
	grade		DECIMAL(5,2),
	submitted_at	TIMESTAMP,
	created_at	TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	CONSTRAINT fk_sa_student FOREIGN KEY (student_id) REFERENCES students(id)		 ON DELETE(CASCADE),
	CONSTRAINT fk_sa_assignment FOREIGN KEY (assignment_id) REFERENCES assignments(id) 	 ON DELETE(CASCADE),
	CONSTRAINT uq_student_assignment UNIQUE (student_id, assignment_id)
);

-- Seed Data

INSERT INTO teachers (first_name, last_name, email)
VALUES ('Kara', 'Lemmons', 'Kara.Lemmons@gmail.com');

INSERT INTO students (first_name, last_name, email, teacher_id)
VALUES
    ('Lyla', 'Olivia', 'lyla.olivia@school.edu', 1),
    ('Bobby',   'Boy','boby.boy@school.edu',   1),
    ('Billy', 'McShake',   'billy.mcshake@school.edu', 1);

INSERT INTO assignments (title, description, subject, due_date, teacher_id)
VALUES
    ('Math Homework 1', 'Addition Assignment', 'MATH', '2026-06-15', 1),
    ('Writing Homework 1', 'Write a fictional narrative', 'WRITING', '2026-06-20', 1);

INSERT INTO student_assignments (student_id, assignment_id, grade, submitted_at)
VALUES
    (1, 1, 92.50, '2026-06-14 10:30:00'),  -- Lyla graded on Math assignment 1
    (2, 1, 85.00, '2026-06-15 08:00:00'),  -- Bobby graded on assignment 1
    (3, 1, NULL,  NULL),                    -- Billy assigned but not yet graded
    (1, 2, NULL,  NULL),                    -- Lyla assigned but not yet graded
    (2, 2, NULL,  NULL),                    -- Bobby assigned but not yet graded
    (3, 2, NULL,  NULL);                    -- Billy assigned but not yet graded
	




