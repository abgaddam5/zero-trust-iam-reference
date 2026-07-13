# API Reference

## Public endpoints

### Register

`POST /api/auth/register`

```json
{
  "email": "user@example.com",
  "password": "StrongPassword123!"
}
```

### Login

`POST /api/auth/login`

```json
{
  "email": "user@example.com",
  "password": "StrongPassword123!"
}
```

Response:

```json
{
  "tokenType": "Bearer",
  "accessToken": "eyJ...",
  "expiresAt": "2026-06-30T12:00:00Z"
}
```

## Protected endpoints

### Current user profile

`GET /api/users/me`

Header:

```text
Authorization: Bearer <ACCESS_TOKEN>
```

### Admin status

`GET /api/admin/status`

Requires `ROLE_ADMIN`.
