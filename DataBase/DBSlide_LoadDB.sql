BEGIN;

DROP TABLE IF EXISTS student, course, grade, professor, section CASCADE;

CREATE TABLE section (
    section_id INTEGER NOT NULL,
    section_name VARCHAR(50),
    delegate_id INTEGER NOT NULL,

    CONSTRAINT pk_section PRIMARY KEY (section_id)
);

CREATE TABLE professor (
    professor_id INTEGER NOT NULL,
    professor_name VARCHAR(30) NOT NULL,
    professor_surname VARCHAR(30) NOT NULL,
    section_id INTEGER NOT NULL,
    professor_office INTEGER NOT NULL,
    professor_email VARCHAR(30) NOT NULL,
    professor_hire_date TIMESTAMP NOT NULL,
    professor_wage INTEGER NOT NULL,

    CONSTRAINT pk_professor PRIMARY KEY (professor_id),
    CONSTRAINT fk_professor_section
        FOREIGN KEY (section_id)
        REFERENCES section (section_id)
);

CREATE TABLE course (
    course_id VARCHAR(8) NOT NULL,
    course_name VARCHAR(200) NOT NULL,
    course_ects NUMERIC(3,1) NOT NULL,
    professor_id INTEGER NOT NULL,

    CONSTRAINT pk_course PRIMARY KEY (course_id),
    CONSTRAINT fk_course_professor
        FOREIGN KEY (professor_id)
        REFERENCES professor (professor_id)
);

CREATE TABLE student (
    student_id INTEGER NOT NULL,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    birth_date TIMESTAMP,
    login VARCHAR(50),
    section_id INTEGER,
    year_result INTEGER,
    course_id VARCHAR(6) NOT NULL,

    CONSTRAINT pk_student PRIMARY KEY (student_id),
    CONSTRAINT fk_student_section
        FOREIGN KEY (section_id)
        REFERENCES section (section_id)
);

CREATE TABLE grade (
    grade CHAR(2) NOT NULL,
    lower_bound INTEGER NOT NULL,
    upper_bound INTEGER NOT NULL,

    CONSTRAINT pk_grade PRIMARY KEY (grade),
    CONSTRAINT ck_grade_value
        CHECK (grade IN ('E', 'TB', 'B', 'S', 'F', 'I', 'IG')),
    CONSTRAINT ck_grade_bounds
        CHECK (lower_bound <= upper_bound)
);

COMMIT;