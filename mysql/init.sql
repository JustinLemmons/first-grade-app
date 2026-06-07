CREATE DATABASE IF NOT EXISTS first_grade_db;
USE first_grade_db;

-- Tables --

CREATE TABLE users (
	 id            BIGINT AUTO_INCREMENT PRIMARY KEY,
   	 email         VARCHAR(255) NOT NULL UNIQUE,
   	 password_hash VARCHAR(255) NOT NULL,
   	 role          ENUM('ADMIN', 'TEACHER', 'STUDENT') NOT NULL,
   	 enabled       BOOLEAN NOT NULL DEFAULT TRUE,
   	 created_at    TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE teachers (
	id		BIGINT AUTO_INCREMENT PRIMARY KEY,
	user_id 	BIGINT NOT NULL,
	first_name 	VARCHAR(100) NOT NULL,
	last_name	VARCHAR(100) NOT NULL,
	created_at	TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	CONSTRAINT      fk_teachers_users FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE students (
	id		BIGINT AUTO_INCREMENT PRIMARY KEY,
	user_id 	BIGINT NOT NULL,
	first_name	VARCHAR(100) NOT NULL,
	last_name	VARCHAR(100) NOT NULL,
	teacher_id	BIGINT NOT NULL,
	created_at	TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	CONSTRAINT      fk_students_users    FOREIGN KEY (user_id)    REFERENCES users(id),
	CONSTRAINT 	fk_students_teachers FOREIGN KEY (teacher_id) REFERENCES teachers(id)
);

CREATE TABLE assignments (
	id		BIGINT AUTO_INCREMENT PRIMARY KEY,
	title		VARCHAR(255) NOT NULL,
	description	TEXT,
	subject		ENUM('MATH', 'WRITING') NOT NULL,
	due_date	DATE NOT NULL,
	teacher_id	BIGINT NOT NULL,
	created_at	TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	CONSTRAINT      fk_assignments_teachers FOREIGN KEY (teacher_id) REFERENCES teachers(id)
);

CREATE TABLE student_assignments (
	id		BIGINT AUTO_INCREMENT PRIMARY KEY,
	student_id	BIGINT NOT NULL,
	assignment_id	BIGINT NOT NULL,
	grade		DECIMAL(5,2),
	submitted_at	TIMESTAMP,
	created_at	TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	CONSTRAINT      fk_sa_students FOREIGN KEY (student_id) REFERENCES students(id)			 ON DELETE CASCADE,
	CONSTRAINT      fk_sa_assignments FOREIGN KEY (assignment_id) REFERENCES assignments(id) 	 ON DELETE CASCADE,
);

-- Seed Data

-- password_hash values below represent BCrypt hash of 'password123'
INSERT INTO users (email, password_hash, role)
VALUES
    ('admin@school.edu',     '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'ADMIN'),
    ('justinlee.lemmons@yahoo.com','$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'TEACHER'),
    ('lyla.olivia@school.edu',   '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'STUDENT'),
    ('bobby.boy@school.edu',     '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'STUDENT'),
    ('billy.mcshake@school.edu',   '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'STUDENT');

INSERT INTO teachers (user_id, first_name, last_name) VALUES (2, 'KJ', 'Lemmons');

INSERT INTO students (user_id, first_name, last_name, teacher_id)
VALUES
    (3, 'Lyla', 'Olivia', 1),
    (4, 'Bobby',   'Boy', 1),
    (5, 'Billy', 'McShake', 1);

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
	




