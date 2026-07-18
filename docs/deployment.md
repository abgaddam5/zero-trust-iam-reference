# Deployment Guide

## Zero Trust IAM Reference

**Version:** 1.0.0  
**Author:** Abhishek Gaddam

## 1. Supported Modes

| Mode | Database | Intended use |
|---|---|---|
| Local development | H2 in-memory | Development, learning, and functional testing |
| Maven package | H2 or PostgreSQL | Local executable JAR |
| Docker Compose | PostgreSQL | Reproducible containerized demonstration |
| Production-like | External PostgreSQL | Requires additional hardening |

## 2. Prerequisites

Local execution:

- Java 21
- Maven 3.9+
- Git

Container execution:

- Docker Desktop or Docker Engine
- Docker Compose v2

Verify:

```bash
java -version
mvn -version
docker --version
docker compose version
```

## 3. Clone the Repository

```bash
git clone https://github.com/abgaddam5/zero-trust-iam-reference.git
cd zero-trust-iam-reference
```

## 4. Local H2 Mode

Build and test:

```bash
mvn clean verify
```

Run:

```bash
mvn spring-boot:run
```

Application endpoints:

- API base: `http://localhost:8080`
- Swagger UI: `http://localhost:8080/swagger-ui.html`
- Health: `http://localhost:8080/actuator/health`
- H2 console: `http://localhost:8080/h2-console`

H2 connection values:

| Setting | Value |
|---|---|
| JDBC URL | `jdbc:h2:mem:ztidb;MODE=PostgreSQL;DATABASE_TO_LOWER=TRUE` |
| Username | `sa` |
| Password | Leave blank |

> H2 and the H2 console are for local development only.

## 5. Run the Packaged JAR

```bash
mvn clean package
java -jar target/*.jar
```

To run with environment variables:

```bash
JWT_SECRET="replace-with-a-strong-secret-at-least-32-bytes" JWT_EXPIRATION_MINUTES=30 java -jar target/*.jar
```

## 6. Docker Compose Mode

Start the application and PostgreSQL:

```bash
docker compose up --build
```

Run in the background:

```bash
docker compose up --build -d
```

View logs:

```bash
docker compose logs -f
```

Stop services:

```bash
docker compose down
```

Stop and delete persisted volumes:

```bash
docker compose down -v
```

Use `down -v` only when database data may be discarded.

## 7. Environment Variables

| Variable | Required | Description | Example |
|---|---:|---|---|
| `DB_URL` | PostgreSQL mode | JDBC connection URL | `jdbc:postgresql://postgres:5432/ztidb` |
| `DB_USERNAME` | PostgreSQL mode | Database username | `zti_user` |
| `DB_PASSWORD` | PostgreSQL mode | Database password | Use a secret value |
| `JWT_SECRET` | Non-development | JWT signing secret | Random value of at least 256 bits |
| `JWT_EXPIRATION_MINUTES` | No | Access-token lifetime | `30` |
| `SERVER_PORT` | No | Application port | `8080` |

Never commit real secrets to:

- `application.yml`
- Docker Compose files
- shell scripts
- screenshots
- Git history

## 8. Verify the Deployment

Health check:

```bash
curl -i http://localhost:8080/actuator/health
```

Expected body:

```json
{"status":"UP"}
```

Register:

```bash
curl -i -X POST http://localhost:8080/api/auth/register   -H "Content-Type: application/json"   -d '{
    "email": "release.user@example.com",
    "password": "ReleaseStrong@12345!"
  }'
```

Login:

```bash
curl -s -X POST http://localhost:8080/api/auth/login   -H "Content-Type: application/json"   -d '{
    "email": "release.user@example.com",
    "password": "ReleaseStrong@12345!"
  }'
```

Copy the returned access token:

```bash
export TOKEN="<ACCESS_TOKEN>"
```

Access the user endpoint:

```bash
curl -i http://localhost:8080/api/users/me   -H "Authorization: Bearer $TOKEN"
```

Verify role enforcement:

```bash
curl -i http://localhost:8080/api/admin/status   -H "Authorization: Bearer $TOKEN"
```

A normal `USER` account should be denied administrative access.

## 9. Production-Hardening Checklist

Before any production-like deployment:

### Transport and network

- Enforce HTTPS.
- Redirect or reject plain HTTP.
- Restrict database network access.
- Place the application behind a reverse proxy or API gateway.
- Limit exposed management endpoints.

### Secrets and keys

- Replace all development secrets.
- Use a managed secrets platform.
- Rotate signing keys and database passwords.
- Avoid long-lived secrets in CI.
- Consider asymmetric JWT signing.

### Application security

- Disable the H2 console.
- Disable development database settings.
- Add MFA.
- Add login and registration rate limiting.
- Add account lockout or progressive delays.
- Add refresh-token rotation and revocation.
- Validate allowed CORS origins.
- Review error responses for sensitive information.

### Data and database

- Use a dedicated least-privilege database user.
- Enable encrypted database connections.
- Back up persistent data.
- Define retention for audit events.
- Protect database migrations and schema changes.

### Observability

- Centralize application and audit logs.
- Redact tokens, passwords, and secrets.
- Add metrics, tracing, and alerts.
- Monitor failed login volume and access-denied events.
- Configure uptime and health monitoring.

### Software supply chain

- Enable Dependabot.
- Add dependency vulnerability scanning.
- Scan container images.
- Protect the `main` branch.
- Require successful CI before merging.
- Pin third-party GitHub Actions to trusted versions or commit SHAs.

## 10. Deployment Limitations

Version 1.0.0 is not presented as production-ready. It does not include:

- MFA
- Rate limiting
- Token revocation
- Account lockout
- External secrets management
- Production TLS termination
- Centralized monitoring
- High-availability configuration
- Formal penetration testing

## 11. Troubleshooting

### Port 8080 is already in use

```bash
lsof -i :8080
```

Stop the conflicting process or set another port:

```bash
SERVER_PORT=8081 mvn spring-boot:run
```

### Maven build fails because of Java version

Confirm Java 21:

```bash
java -version
mvn -version
```

### Docker cannot connect

Start Docker Desktop and verify:

```bash
docker info
```

### PostgreSQL connection failure

Check containers:

```bash
docker compose ps
docker compose logs postgres
```

Confirm that `DB_URL`, username, and password match the Compose configuration.

### JWT validation fails

Confirm:

- The token has not expired.
- The token was issued by the currently running application.
- The JWT secret did not change after token issuance.
- The header uses `Authorization: Bearer <token>`.

## 12. Rollback

For a tagged release:

```bash
git fetch --tags
git checkout v1.0.0
mvn clean verify
```

For Docker:

```bash
docker compose down
git checkout v1.0.0
docker compose up --build -d
```

Database rollback requires an explicit schema and data migration strategy and is outside the scope of v1.0.0.
