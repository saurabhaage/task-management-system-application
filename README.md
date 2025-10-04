# task-management-system-application

A web-based application to manage tasks efficiently with features like task creation, assignment, status tracking, and role-based access for users and admins.

## Table of Contents
- [Features](#features)
- [Technologies Used](#technologies-used)
- [Installation](#installation)
- [Usage](#usage)
- [API Endpoints](#api-endpoints)
- [Screenshots](#screenshots)
- [Contributing](#contributing)
- [License](#license)


## Features
- User registration and login with JWT authentication
- Role-based access (Admin/User)
- CRUD operations for tasks
- Assign tasks to users
- Mark tasks as complete or pending
- Search and filter tasks

- 


## Technologies Used
- **Backend:** Java, Spring Boot
- **Frontend:** ReactJS
- **Database:** MySQL/PostgreSQL
- **Authentication:** JWT, Spring Security


## Installation

1. Clone the repository:
```bash
git clone https://github.com/username/task-management-system.git


## Usage
- Open your browser and go to `http://localhost:3000`
- Register as a new user or login
- Admin can view, add, edit, and delete tasks
- Users can view assigned tasks and update task status


## API Endpoints

| Method | Endpoint | Description |
|--------|---------|-------------|
| POST   | /api/auth/register | Register new user |
| POST   | /api/auth/login | Login user |
| GET    | /api/tasks | Get all tasks |
| POST   | /api/tasks | Create a new task |
| PUT    | /api/tasks/{id} | Update task |
| DELETE | /api/tasks/{id} | Delete task |


## Screenshots

![Login Page](screenshots/login.png)
![Dashboard](screenshots/dashboard.png)

