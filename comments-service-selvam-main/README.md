# Assignment 2 - Comments service for blog application

You are working on a Spring Boot REST API application that handles posts and comments in a blog application. 

## Goals

Your task is to extend REST API for processing comments. The application should process following requests:

- `POST` at `/posts/{1}/comments` - which should save a new comment for post with passed `{id}`
- `GET` at `/posts/{1}/comments` - which should return all comments for a post with passed `{id}`
- There is a service class `CommentService` that you should implement. See contents of that class to see detailed requirements.

## System setup

This project uses Spring Boot, Spring Data (with Hibernate) and embedded H2 database. Structure of database is created automatically on application startup based on JPA Entity classes.

Use Java 11 and Maven 3.6.x and above (we tested it with Maven version 3.6.3).

## Implementation

Create a new feature branch (for example `assignment`) where you will make changes.

You can create additional methods or classes if necessary to create reusable/readable code, but you should not alter existing method signatures ( their return type, throws clause etc ), or their visibility/structure.

## Submission and review

Commit and push your changes. Then please acknowledge us that your work is finished (by email or phone call) - after that, we will review your implementation.
