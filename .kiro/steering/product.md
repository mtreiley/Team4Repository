# Product Overview

A REST API backend for a todo management application built by Team Tetra. Users can register and log in, then create and manage todo items with optional subtasks.

## Core Features

- User registration and login (username/password authentication)
- CRUD operations for todo items, scoped to a user
- Subtask management nested under todos

## API Endpoints

| Method | Path        | Description          |
|--------|-------------|----------------------|
| POST   | /register   | Register a new user  |
| POST   | /login      | Log in a user        |
| POST   | /todo       | Create a todo        |
| GET    | /todo       | Get todos for a user |
| PUT    | /todo       | Update a todo        |
| DELETE | /todo       | Delete a todo        |

Subtask endpoints follow the same REST pattern under `/subtask`.

## Business Rules

- Usernames must be 5–15 characters and unique
- Passwords must be 5–15 characters and contain at least one uppercase letter, one lowercase letter, and one digit
- Todos must be associated with a valid existing user
- Todo titles must be unique
