# Threat Model

## Scope

This threat model covers the reference API's authentication and authorization flows.

## Assets

- User credentials
- JWT access tokens
- User role assignments
- Audit events
- API availability

## Threats and controls

| Threat | Risk | Current control | Future improvement |
|---|---|---|---|
| Credential stuffing | Account compromise | BCrypt hashing, password policy | Rate limiting, breached password check, account lockout |
| Token theft | Unauthorized access | Short-lived JWT | Refresh token rotation, token revocation, sender-constrained tokens |
| Privilege escalation | Unauthorized admin access | RBAC rules | Fine-grained permissions and approval workflow |
| Weak passwords | Easier brute force | Minimum length and weak-pattern checks | NIST-style compromised-password screening |
| Missing audit trail | Poor investigation capability | Audit events for registration and login | Centralized SIEM integration |
| Secret leakage | Token signing compromise | Env-based secret config | Secrets vault and key rotation |

## Assumptions

- TLS is required in production environments.
- JWT signing secrets are not committed to source control.
- Database access is restricted by environment and network controls.

## Out of scope

- Employer-specific identity systems
- Proprietary authentication flows
- Mainframe-specific or financial-institution-specific implementation details
