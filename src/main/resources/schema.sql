-- Add instructor_id column to class_session table if it doesn't exist
ALTER TABLE class_session
ADD COLUMN IF NOT EXISTS instructor_id VARCHAR(50) DEFAULT NULL,
ADD CONSTRAINT FK_classSession_instructor FOREIGN KEY (instructor_id) REFERENCES instructor (id); 