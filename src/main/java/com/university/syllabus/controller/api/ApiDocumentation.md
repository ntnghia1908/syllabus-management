# Syllabus Management System REST API Documentation

This document outlines the RESTful API endpoints available in the Syllabus Management System.

## Base URL

All API endpoints are prefixed with `/api`.

## Courses API

### GET /api/courses
Returns a list of all courses.

**Response**: 200 OK
```json
[
  {
    "id": "CSC101",
    "name": "Introduction to Computer Science",
    "nameVn": "Giới thiệu về Khoa học Máy tính",
    "creditTheory": 3,
    "creditLab": 1,
    "description": "Introduction to programming concepts",
    "courseLevelId": 1,
    "courseLevelName": "Undergraduate"
  }
]
```

### GET /api/courses/{id}
Returns a specific course by ID.

**Response**: 200 OK
```json
{
  "id": "CSC101",
  "name": "Introduction to Computer Science",
  "nameVn": "Giới thiệu về Khoa học Máy tính",
  "creditTheory": 3,
  "creditLab": 1,
  "description": "Introduction to programming concepts",
  "courseLevelId": 1,
  "courseLevelName": "Undergraduate"
}
```

### POST /api/courses
Creates a new course.

**Request Body**:
```json
{
  "id": "CSC101",
  "name": "Introduction to Computer Science",
  "nameVn": "Giới thiệu về Khoa học Máy tính",
  "creditTheory": 3,
  "creditLab": 1,
  "description": "Introduction to programming concepts",
  "courseLevelId": 1
}
```

**Response**: 201 Created
```json
{
  "id": "CSC101",
  "name": "Introduction to Computer Science",
  "nameVn": "Giới thiệu về Khoa học Máy tính",
  "creditTheory": 3,
  "creditLab": 1,
  "description": "Introduction to programming concepts",
  "courseLevelId": 1,
  "courseLevelName": "Undergraduate"
}
```

### PUT /api/courses/{id}
Updates an existing course.

**Request Body**:
```json
{
  "id": "CSC101",
  "name": "Introduction to Computer Science",
  "nameVn": "Giới thiệu về Khoa học Máy tính",
  "creditTheory": 3,
  "creditLab": 1,
  "description": "Updated description",
  "courseLevelId": 1
}
```

**Response**: 200 OK
```json
{
  "id": "CSC101",
  "name": "Introduction to Computer Science",
  "nameVn": "Giới thiệu về Khoa học Máy tính",
  "creditTheory": 3,
  "creditLab": 1,
  "description": "Updated description",
  "courseLevelId": 1,
  "courseLevelName": "Undergraduate"
}
```

### DELETE /api/courses/{id}
Deletes a course.

**Response**: 204 No Content

## Instructors API

### GET /api/instructors
Returns a list of all instructors.

### GET /api/instructors/{id}
Returns a specific instructor by ID.

### POST /api/instructors
Creates a new instructor.

### PUT /api/instructors/{id}
Updates an existing instructor.

### DELETE /api/instructors/{id}
Deletes an instructor.

### GET /api/instructors/search?query={searchText}
Searches for instructors by name or other attributes.

## Pathways API

### GET /api/pathways
Returns a list of all pathways.

### GET /api/pathways/{id}
Returns a specific pathway by ID.

### POST /api/pathways
Creates a new pathway.

### PUT /api/pathways/{id}
Updates an existing pathway.

### DELETE /api/pathways/{id}
Deletes a pathway. Will return 409 Conflict if there are courses assigned to the pathway.

## Programs API

### GET /api/programs
Returns a list of all programs.

### GET /api/programs/{id}
Returns a specific program by ID.

### POST /api/programs
Creates a new program.

### PUT /api/programs/{id}
Updates an existing program.

### DELETE /api/programs/{id}
Deletes a program. Will return 409 Conflict if there are courses assigned to the program.

### GET /api/programs/versions
Returns a list of all program versions.

### GET /api/programs/{id}/curriculum
Returns the curriculum (course pathways) for a specific program.

## Course Pathways API

### GET /api/course-pathways/program/{programId}
Returns course-pathways for a specific program.

### GET /api/course-pathways/program/{programId}/ordered
Returns course-pathways for a specific program, ordered by semester and year.

### GET /api/course-pathways/pathway/{pathwayId}
Returns course-pathways for a specific pathway.

### GET /api/course-pathways/course/{courseId}
Returns course-pathways for a specific course.

### GET /api/course-pathways/{programId}/{pathwayId}/{courseId}
Returns a specific course-pathway by its composite ID.

### POST /api/course-pathways
Creates a new course-pathway.

### PUT /api/course-pathways/{programId}/{pathwayId}/{courseId}
Updates an existing course-pathway.

### DELETE /api/course-pathways/{programId}/{pathwayId}/{courseId}
Deletes a course-pathway.