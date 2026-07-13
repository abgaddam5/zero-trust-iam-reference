# Zero Trust IAM Reference

An open-source reference implementation demonstrating practical Zero Trust identity security patterns using Spring Boot, Spring Security, JWT, RBAC, password policy enforcement, audit logging, Docker, and PostgreSQL.

> This project is educational and built only with public, open-source technologies. It does not include code, configurations, internal architecture, or proprietary details from any employer or client organization.

## Why this project exists

Identity is a critical control plane for modern enterprises. This project demonstrates a clean, auditable, and extensible approach to authentication and authorization that can be used as a learning reference for secure application design.

## Features

- User registration
- Secure login
- JWT-based stateless authentication
- Role-based access control: `USER`, `ADMIN`
- BCrypt password hashing
- Password policy validation
- Authentication audit logging
- Spring Security 6 configuration
- OpenAPI / Swagger documentation
- Docker Compose with PostgreSQL
- Health endpoint with Spring Boot Actuator
- GitHub Actions CI workflow

## Technology stack

- Java 21
- Spring Boot 3
- Spring Security 6
- Spring Data JPA
- PostgreSQL / H2
- JWT using JJWT
- Maven
- Docker
- OpenAPI / Swagger UI
- JUnit 5

## Quick start

### Run locally with H2

```bash
mvn spring-boot:run
```

Application: `http://localhost:8080`

Swagger UI: `http://localhost:8080/swagger-ui.html`

### Run with Docker Compose

```bash
docker compose up --build
```

## Example API usage

### Register

```bash
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{"email":"user@example.com","password":"StrongPassword123!"}'
```

### Login

```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"email":"user@example.com","password":"StrongPassword123!"}'
```

### Access protected profile endpoint

```bash
curl http://localhost:8080/api/users/me \
  -H "Authorization: Bearer <ACCESS_TOKEN>"
```

## Security principles demonstrated

- Verify explicitly: every protected request requires a valid JWT.
- Use least privilege: role-based access controls restrict privileged endpoints.
- Assume breach: authentication events are logged for auditability.
- Reduce password risk: passwords are hashed using BCrypt and validated against basic policy rules.
- Favor stateless controls: JWTs reduce server-side session exposure.

## Repository structure

```text
zero-trust-iam-reference/
├── .github/workflows/ci.yml
├── docs/
│   ├── api.md
│   ├── architecture.md
│   ├── deployment.md
│   └── threat-model.md
├── src/
├── Dockerfile
├── docker-compose.yml
├── README.md
├── LICENSE
├── SECURITY.md
├── CONTRIBUTING.md
└── CHANGELOG.md
```

## Important limitations

This project is a reference implementation. Before production use, teams should add controls such as rate limiting, account lockout, MFA, refresh token rotation, centralized secret management, observability, structured security logging, automated dependency scanning, and formal threat modeling.

## Roadmap

- TOTP MFA
- Refresh token rotation
- Account lockout
- Rate limiting
- OpenTelemetry tracing
- Prometheus metrics
- Centralized structured logging
- OAuth2 resource-server profile

## License

Apache License 2.0. See [LICENSE](LICENSE).
