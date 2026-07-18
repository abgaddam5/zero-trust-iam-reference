# Zero Trust IAM Reference

> **An educational reference implementation demonstrating modern Identity and Access Management (IAM) principles using Spring Boot, JWT authentication, Role-Based Access Control (RBAC), secure password management, and security audit logging.**

![Java](https://img.shields.io/badge/Java-21-blue)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.x-success)
![Security](https://img.shields.io/badge/Spring_Security-6.x-brightgreen)
![JWT](https://img.shields.io/badge/JWT-Authentication-orange)
![License](https://img.shields.io/badge/License-MIT-yellow)
![Build](https://img.shields.io/badge/Build-Maven-red)

---

# Overview

Modern enterprise applications rely on strong Identity and Access Management (IAM) to protect users, applications, and critical business resources. As organizations adopt Zero Trust security principles, authentication, authorization, auditing, and secure credential management have become fundamental building blocks of secure software.

**Zero Trust IAM Reference** is an open-source educational reference implementation designed to demonstrate these concepts using widely adopted Java technologies and enterprise software engineering practices.

The project illustrates how secure authentication, authorization, password protection, and audit logging can be implemented using publicly available technologies without relying on proprietary enterprise solutions.

This repository is intended for:

- Software Engineers
- Enterprise IAM Engineers
- Cybersecurity Professionals
- Students
- Developers learning Spring Security

---

# Objectives

The project demonstrates practical implementation of modern IAM concepts including:

- Secure user registration
- JWT-based authentication
- Role-Based Access Control (RBAC)
- BCrypt password hashing
- Security audit logging
- REST API protection
- Secure Spring Security configuration
- Enterprise application layering
- Modern Java development practices

---

# Key Features

### Authentication

- User Registration
- Secure Login
- JWT Token Generation
- JWT Validation
- Stateless Authentication

### Authorization

- Role-Based Access Control (RBAC)
- User Role Protection
- Admin-only Endpoints
- Protected REST APIs

### Security

- BCrypt Password Hashing
- Spring Security
- Authentication Filter
- Authorization Rules
- Secure Password Storage

### Auditing

- User Registration Events
- Login Events
- Timestamped Audit Records
- Security Event Tracking

### Monitoring

- Spring Boot Actuator
- Health Endpoint
- Application Status

### Documentation

- OpenAPI / Swagger
- REST API Documentation

---

# Technology Stack

| Layer | Technology |
|---------|------------|
| Language | Java 21 |
| Framework | Spring Boot 3 |
| Security | Spring Security |
| Authentication | JWT |
| Password Encryption | BCrypt |
| ORM | Spring Data JPA |
| Database | H2 |
| Build Tool | Maven |
| Documentation | OpenAPI / Swagger |
| Monitoring | Spring Boot Actuator |

---

# Project Architecture

```
                Client

                  │

                  ▼

         REST Controllers

                  │

                  ▼

        Spring Security Filter

                  │

                  ▼

          JWT Authentication

                  │

                  ▼

          Business Services

                  │

                  ▼

          JPA Repositories

                  │

                  ▼

             H2 Database
```

---

# Project Structure

```
src
 ├── controller
 │      ├── AuthController
 │      ├── UserController
 │      └── AdminController
 │
 ├── security
 │      ├── SecurityConfig
 │      ├── JwtService
 │      └── JwtAuthenticationFilter
 │
 ├── service
 │
 ├── repository
 │
 ├── entity
 │
 ├── dto
 │
 └── config
```

---

# Authentication Flow

```
User

↓

Register

↓

Password Validation

↓

BCrypt Hashing

↓

Store User

↓

Login

↓

Credential Verification

↓

JWT Generation

↓

Bearer Token

↓

Protected APIs
```

---

# Authorization Model

The application implements Role-Based Access Control (RBAC).

Current roles include:

- ROLE_USER
- ROLE_ADMIN

Example:

| Endpoint | Access |
|------------|---------|
| /api/auth/register | Public |
| /api/auth/login | Public |
| /api/users/me | Authenticated User |
| /api/admin/status | Admin Only |

---

# Database Schema

The application maintains three primary entities.

### User Accounts

Stores user identity information including:

- Email
- Password (BCrypt)
- Status
- Created Date

### User Roles

Maps users to security roles.

### Audit Events

Captures important security events including:

- Registration
- Login
- Timestamp
- Success Status

---

# Security Features

The project demonstrates several enterprise security practices.

✔ BCrypt password hashing

✔ Stateless JWT Authentication

✔ Role-Based Authorization

✔ Secure endpoint protection

✔ Audit logging

✔ Layered architecture

✔ Dependency Injection

✔ RESTful API design

---

# Running the Application

## Clone Repository

```bash
git clone https://github.com/abgaddam5/zero-trust-iam-reference.git
```

## Build

```bash
mvn clean install
```

## Run

```bash
mvn spring-boot:run
```

Application starts at

```
http://localhost:8080
```

---

# API Documentation

Swagger UI

```
http://localhost:8080/swagger-ui.html
```

Health Endpoint

```
http://localhost:8080/actuator/health
```

H2 Console

```
http://localhost:8080/h2-console
```

---

# Example Authentication

## Register

```
POST /api/auth/register
```

## Login

```
POST /api/auth/login
```

Returns

```
JWT Access Token
```

Use

```
Authorization: Bearer <token>
```

to access protected endpoints.

---

# Testing

The application has been functionally validated for:

- User Registration
- Duplicate Registration
- Login
- JWT Authentication
- Protected Endpoints
- Role-Based Authorization
- Audit Logging
- Database Persistence
- Application Health

---

# Future Roadmap

Version 1.1

- Time-based One-Time Password (TOTP) Multi-Factor Authentication

Version 1.2

- Refresh Token Rotation
- Password Reset
- Account Lockout

Version 1.3

- OpenTelemetry Integration
- Metrics Collection
- Distributed Tracing

Version 2.0

- OAuth2
- OpenID Connect
- Docker Deployment
- Kubernetes Deployment

---

# Educational Purpose

This repository is intended as an educational reference implementation that demonstrates Identity and Access Management (IAM) concepts using publicly available technologies.

The implementation does **not** contain proprietary business logic, confidential enterprise code, or organization-specific security implementations.

---

# Contributing

Contributions that improve security, documentation, testing, and educational value are welcome.

Please submit issues and pull requests for review.

---

# License

Released under the MIT License.

---

# Author

**Abhishek Gaddam**

Software Engineer | Identity & Access Management | Enterprise Security

Areas of Interest

- Identity & Access Management (IAM)
- Zero Trust Security
- Enterprise Application Security
- Java & Spring Boot
- Secure Software Engineering
- Cloud-Native Security

---

# Disclaimer

This project is provided for educational and research purposes only.

It demonstrates software engineering and security design principles using publicly available technologies. It should not be considered a production-ready identity platform without additional security hardening, operational controls, and comprehensive testing.
