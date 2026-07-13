# Security Policy

This repository is an educational reference implementation and should not be deployed to production without additional hardening.

## Reporting vulnerabilities

Please open a GitHub issue with a clear description of the concern. Do not include sensitive data, secrets, or exploit details that could harm real systems.

## Security assumptions

- JWT secret is provided through environment variables.
- TLS termination is handled by infrastructure in production-like deployments.
- Passwords are never stored in plaintext.
- Audit events are stored for authentication-relevant events.

## Recommended production hardening

- Use a managed secrets vault.
- Enforce TLS everywhere.
- Add MFA.
- Add rate limiting and account lockout.
- Rotate signing keys.
- Add dependency scanning.
- Add container image scanning.
- Send audit logs to a centralized SIEM.
