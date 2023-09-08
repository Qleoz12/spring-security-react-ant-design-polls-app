-- Create tables for teachers
CREATE TABLE teachers (
    teacher_id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    phone_number VARCHAR(15),
    hire_date DATE NOT NULL,
    department VARCHAR(50) NOT NULL
);

-- Create tables for students (alumni)
CREATE TABLE students (
    students_id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    graduation_date DATE NOT NULL
);

-- Create tables for subjects
CREATE TABLE subjects (
    subject_id INT AUTO_INCREMENT PRIMARY KEY,
    subject_name VARCHAR(100) NOT NULL,
    department VARCHAR(50) NOT NULL
);

-- Create a table for polls
CREATE TABLE polls_holding (
    poll_holding_id INT AUTO_INCREMENT PRIMARY KEY,
    poll_id bigint NOT NULL,
    student_id INT NOT NULL,
    FOREIGN KEY (poll_id) REFERENCES polls(id),
    FOREIGN KEY (student_id) REFERENCES students(students_id)
);

-- Create a table for the relationship between subjects, polls, and professors
CREATE TABLE subject_poll_professor (
    subject_poll_professor_id INT AUTO_INCREMENT PRIMARY KEY,
    subject_id INT NOT NULL,
    poll_id bigint NOT NULL,
    teacher_id INT NOT NULL,
    FOREIGN KEY (subject_id) REFERENCES subjects (subject_id),
    FOREIGN KEY (poll_id) REFERENCES polls(id),
    FOREIGN KEY (teacher_id) REFERENCES teachers (teacher_id)
);
