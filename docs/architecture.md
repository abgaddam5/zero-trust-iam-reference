# Architecture Overview

## Purpose

This project demonstrates a simple Zero Trust-aligned IAM API where every protected request is explicitly authenticated and authorized.

## Logical architecture

```text
Client
  |
  | 1. Register/Login
  v
Auth Controller ----> Auth Service ----> User Repository ----> Database
  |                       |
  |                       +----> Password Policy Service
  |                       +----> JWT Service
  |                       +----> Audit Service ----> Audit Repository
  |
  | 2. Receive JWT
  v
Protected API Request with Bearer Token
  |
  v
JWT Authentication Filter
  |
  v
Spring Security Authorization Rules
  |
  v
User/Admin Controllers
```

## Zero Trust mapping

| Zero Trust principle | Implementation example |
|---|---|
| Verify explicitly | JWT validation on protected APIs |
| Least privilege | Role-based access control |
| Assume breach | Audit logging for authentication events |
| Reduce credential risk | BCrypt password hashing and password policy validation |
| Continuous improvement | Roadmap includes MFA, rate limiting, and observability |

## Authentication flow

1. User submits email and password.
2. Password policy is validated during registration.
3. Password is hashed with BCrypt.
4. Login authenticates credentials through Spring Security.
5. JWT is issued with subject and roles.
6. Client sends JWT in `Authorization: Bearer` header.
7. Filter validates token and establishes security context.
8. Spring Security enforces endpoint authorization.

## Confidentiality note

This architecture is generic and uses public open-source technology. It does not represent any employer-specific implementation.
