# task-management-system-application

A web-based application to manage tasks efficiently with features like task creation, assignment, status tracking, and role-based access for users and admins.

## Table of Contents
- [Features](#features)
- [Technologies Used](#technologies-used)
- [Installation](#installation)
- [Usage](#usage)
- [API Endpoints](#api-endpoints)
- [Screenshots](#screenshots)



## Features
-User registration and login with JWT authentication
-Role-based access (Admin/User)
-Admin can assign tasks to users
-Users can view, update, or mark tasks as complete/pending
-CRUD operations for tasks
-Search and filter tasks by title or status
-Responsive UI built with React and Bootstrap
-Backend developed using Spring Boot and JPA/Hibernate
-Password hashing using BCrypt for security
-JWT-based authentication for secure API access
-Global exception handling and validation for API errors
-Email validation for user registration
-Separate login pages for Admin and User
-Dynamic task list fetching based on logged-in user
-User dashboard and Admin dashboard with appropriate access control
-API versioning and structured JSON responses
-Local storage management for logged-in user/session
-Optional: Display task completion status visually (✅ Completed / ⏳ Pending)

## Technologies Used
-Backend: Java, Spring Boot, Spring Security, JPA/Hibernate
-Frontend: ReactJS, Bootstrap 5, React Router
-Database: MySQL / PostgreSQL
-Authentication: JWT (JSON Web Token)
-API Testing: Postman
-Password Security: BCrypt Password Encoder
-Version Control: Git / GitHub
-Build Tool: Maven
-Validation & Exception Handling: Jakarta Validation, Global Exception Handling
-Development Tools: VS Code, IntelliJ IDEA / Eclipse
-Browser Testing: Chrome, Firefox


## Installation

1. Frontend:
  -Open your browser and go to http://localhost:3000.
  -You will see the Home Page with options to login as Admin or User.

2. User Actions:
-Register as a new user using the Sign Up page.
-Login using your credentials.
-View your assigned tasks.
-Mark tasks as Complete or Pending.

3. Admin Actions:
-Login as an Admin using your credentials.
-View all registered users.
-Assign tasks to users.
-Add, update, or delete tasks.
-Monitor task completion status of all users.

4. API Testing:
-You can use Postman to test backend APIs at http://localhost:8080.

Example endpoints:

>POST /user/register – Register a new user
>POST /user/login – User login
>POST /admin/login – Admin login
>GET /user/getAll – Get all users (Admin only)
>GET /task/user/{userId} – Get tasks for a user

## API Endpoints

| Method | Endpoint                  | Description                                           |
|--------|---------------------------|-------------------------------------------------------|
| POST   | /user/register            | Register new user                                     |
| POST   | /user/login               | Login user                                            |
| GET    | /tasks/getAll             | Get all tasks assigne to login user                   |
| POST   | /admin/creatUser          | Register new Admin                                    |
| POST   | /admin/login              | Login Admin                                           |
| GET    | /admin/allUser            | Get all User form database                            |
| Delete | /admin/delete/{id}        | Admin can delet the specific user                     |
| Put    | /admin/update/{id}        | Admin can update the user data                        |
| POST   | /task//create/{userId}    | create task and pass the user Id and store in databse |
| GET    | /task/allUser             | Get all Task form database                            |
| PUT    | /task/update/{id}         | Update task                                           |
| DELETE | /task/delete/{id}         | Delete task                                           |


## Screenshots

![Login Page](screenshots/login.png)
![Dashboard](screenshots/dashboard.png)
Home Page - <img width="1366" height="768" alt="HomePage" src="https://github.com/user-attachments/assets/918c42b6-576d-44ac-a381-64b5f35b347e" />

Admin  
  -AdminLogin -  <img width="1366" height="768" alt="AdminLogin" src="https://github.com/user-attachments/assets/d233b090-aa18-4999-8d29-a9a9a7698c34" />

  -AdminDashbord  - <img width="1366" height="768" alt="AdminDashbord" src="https://github.com/user-attachments/assets/2bd5ba37-8d14-4592-8313-105f55e343e2" />

  -AssigneTask  - <img width="1366" height="768" alt="TaskAssigne" src="https://github.com/user-attachments/assets/40f48921-2a56-4671-a2b3-a7bfb53c0f62" />

  -AssigneSuccessfully  - <img width="1366" height="768" alt="TaskAssigneSuccesfully" src="https://github.com/user-attachments/assets/7683e63b-f77b-4665-8657-9c7281688f52" />

  -UpdateUser - <img width="1366" height="768" alt="UpdateUser" src="https://github.com/user-attachments/assets/346076db-ae57-461c-be6c-6d3e8e419b73" />

  -UpdateSuccessfully - <img width="1366" height="768" alt="UpdatuserSucceflly" src="https://github.com/user-attachments/assets/3091559b-0283-4ffe-b133-102f306bcbf4" />

  -DeletUser - <img width="1366" height="768" alt="DeletUser" src="https://github.com/user-attachments/assets/3efae4b0-f0f1-491c-8a6f-554f8dd9e60a" />

  -ShowTask - <img width="1366" height="768" alt="listOfTask" src="https://github.com/user-attachments/assets/234f04a4-2b90-41aa-906f-831ce05b8d60" />

User
  -UserSignUp - <img width="1366" height="768" alt="SignUp" src="https://github.com/user-attachments/assets/91b9267a-d850-4aed-8611-71fc47c3275c" />

  -UserLogin - <img width="1366" height="768" alt="UserLogin" src="https://github.com/user-attachments/assets/44526ab2-0441-4369-a0ae-d818ca4a94ae" />

  -UserDashbord - <img width="1366" height="768" alt="UserDashbord" src="https://github.com/user-attachments/assets/a05e9266-d7cb-4942-bfd6-24d375bbdc50" />
