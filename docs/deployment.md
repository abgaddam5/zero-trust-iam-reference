# Deployment Guide

## Local H2 mode

```bash
mvn spring-boot:run
```

## Docker Compose mode

```bash
docker compose up --build
```

## Environment variables

| Variable | Description | Example |
|---|---|---|
| `DB_URL` | JDBC database URL | `jdbc:postgresql://localhost:5432/ztidb` |
| `DB_USERNAME` | Database username | `zti_user` |
| `DB_PASSWORD` | Database password | `zti_password` |
| `JWT_SECRET` | Strong JWT signing secret | Use 256-bit+ secret |
| `JWT_EXPIRATION_MINUTES` | Access token lifetime | `30` |

## Production hardening checklist

- Replace development JWT secret.
- Enforce TLS.
- Use managed secrets storage.
- Add MFA.
- Enable rate limiting.
- Add account lockout.
- Use centralized audit logging.
- Enable dependency and container scanning.
- Add observability and alerting.
