# API Reference

## Zero Trust IAM Reference

**Version:** 1.0.0  
**Base URL:** `http://localhost:8080`

## 1. Authentication

Protected endpoints accept a JWT access token:

```http
Authorization: Bearer <ACCESS_TOKEN>
```

Tokens are issued by the login endpoint.

## 2. Content Type

Requests containing JSON must include:

```http
Content-Type: application/json
```

## 3. Endpoint Summary

| Method | Endpoint | Access | Purpose |
|---|---|---|---|
| `POST` | `/api/auth/register` | Public | Register a user |
| `POST` | `/api/auth/login` | Public | Authenticate and issue JWT |
| `GET` | `/api/users/me` | Authenticated | Return current user profile |
| `GET` | `/api/admin/status` | `ROLE_ADMIN` | Verify administrative authorization |
| `GET` | `/actuator/health` | Public | Return application health |
| `GET` | `/swagger-ui.html` | Public | Open interactive API documentation |
| `GET` | `/v3/api-docs` | Public | OpenAPI specification |

## 4. Register User

```http
POST /api/auth/register
```

### Request

```json
{
  "email": "user@example.com",
  "password": "StrongPassword123!"
}
```

### Validation

- Email must be syntactically valid.
- Email is normalized before storage.
- Email must not already be registered.
- Password must satisfy the configured password policy.

### Example

```bash
curl -i -X POST http://localhost:8080/api/auth/register   -H "Content-Type: application/json"   -d '{
    "email": "user@example.com",
    "password": "StrongPassword123!"
  }'
```

### Expected outcomes

| Condition | Expected status |
|---|---:|
| Valid new account | Successful response |
| Duplicate email | `400 Bad Request` |
| Invalid request | `400 Bad Request` |
| Weak password | `400 Bad Request` |

### Duplicate-email example

```json
{
  "message": "Email already registered"
}
```

## 5. Login

```http
POST /api/auth/login
```

### Request

```json
{
  "email": "user@example.com",
  "password": "StrongPassword123!"
}
```

### Response

```json
{
  "tokenType": "Bearer",
  "accessToken": "eyJ...",
  "expiresAt": "2026-07-31T12:00:00Z"
}
```

### Example

```bash
curl -s -X POST http://localhost:8080/api/auth/login   -H "Content-Type: application/json"   -d '{
    "email": "user@example.com",
    "password": "StrongPassword123!"
  }'
```

### Security behavior

- Credentials are verified through Spring Security.
- Passwords are compared against BCrypt hashes.
- Successful authentication produces a signed JWT.
- Login activity is recorded in the audit store.

## 6. Current User Profile

```http
GET /api/users/me
```

### Access

Authenticated user.

### Request

```bash
curl -i http://localhost:8080/api/users/me   -H "Authorization: Bearer <ACCESS_TOKEN>"
```

### Example response

```json
{
  "email": "user@example.com",
  "roles": [
    "ROLE_USER"
  ]
}
```

### Expected outcomes

| Condition | Expected status |
|---|---:|
| Valid token | `200 OK` |
| Missing or invalid authentication | Access denied |
| Expired token | Access denied |

## 7. Administrator Status

```http
GET /api/admin/status
```

### Access

Requires `ROLE_ADMIN`.

### Request

```bash
curl -i http://localhost:8080/api/admin/status   -H "Authorization: Bearer <ACCESS_TOKEN>"
```

### Expected outcomes

| Identity | Expected result |
|---|---|
| Administrator | Successful response |
| Authenticated `USER` | `403 Forbidden` |
| Unauthenticated client | Access denied |

Version 1.0.0 does not expose a public API for assigning the `ADMIN` role.

## 8. Health Check

```http
GET /actuator/health
```

### Request

```bash
curl -i http://localhost:8080/actuator/health
```

### Response

```json
{
  "status": "UP"
}
```

## 9. OpenAPI and Swagger

Swagger UI:

```text
http://localhost:8080/swagger-ui.html
```

OpenAPI JSON:

```text
http://localhost:8080/v3/api-docs
```

The interactive interface documents the available request and response models. Bearer-token authorization support may be added through the OpenAPI security configuration.

## 10. Status-Code Guide

| Status | Meaning |
|---:|---|
| `200 OK` | Request completed successfully |
| `400 Bad Request` | Validation, duplicate identity, or malformed request |
| `401 Unauthorized` | Authentication required or rejected, depending on configuration |
| `403 Forbidden` | Access denied by the active Spring Security configuration |
| `500 Internal Server Error` | Unexpected server failure |

The exact unauthenticated response may depend on the configured Spring Security entry point. Functional testing of v1.0.0 observed access denial for protected requests without a valid JWT.

## 11. Password and Token Handling

- Never place passwords or tokens in URLs.
- Never commit tokens to source control.
- Avoid including bearer tokens in screenshots or public issue reports.
- Regenerate exposed tokens.
- Use HTTPS outside local development.
- Treat access tokens as credentials until they expire.

## 12. Functional Verification Completed

The following behaviors were validated:

- User registration
- Strong-password enforcement
- Duplicate-registration rejection
- Login and JWT issuance
- Authenticated profile access
- Role-based denial of admin access
- Denial of unauthenticated protected access
- Application health
- Password hashing in the database
- User-role persistence
- Registration and login audit records

## 13. Current Limitations

- No MFA
- No refresh tokens
- No token revocation
- No password reset
- No email verification
- No account lockout
- No public role-administration API
- No rate limiting

## 14. Example End-to-End Session

```bash
# Register
curl -X POST http://localhost:8080/api/auth/register   -H "Content-Type: application/json"   -d '{"email":"user@example.com","password":"StrongPassword123!"}'

# Login
curl -X POST http://localhost:8080/api/auth/login   -H "Content-Type: application/json"   -d '{"email":"user@example.com","password":"StrongPassword123!"}'

# Set the returned token
export TOKEN="<ACCESS_TOKEN>"

# Access profile
curl http://localhost:8080/api/users/me   -H "Authorization: Bearer $TOKEN"

# Verify least privilege
curl -i http://localhost:8080/api/admin/status   -H "Authorization: Bearer $TOKEN"
```
