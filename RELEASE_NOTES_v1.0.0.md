# Zero Trust IAM Reference v1.0.0

## First Public Reference Release

Zero Trust IAM Reference v1.0.0 is the first public release of an educational Spring Boot implementation demonstrating foundational Identity and Access Management and selected Zero Trust principles.

## Included Capabilities

- Secure user registration
- Password-policy validation
- BCrypt password hashing
- Duplicate-email prevention
- Username/password authentication
- Signed JWT access tokens
- Stateless request authentication
- Role-Based Access Control
- Authenticated user-profile endpoint
- Administrator-only endpoint
- Registration and login audit events
- H2 development database
- PostgreSQL and Docker Compose support
- Swagger/OpenAPI documentation
- Spring Boot Actuator health endpoint
- Maven-based automated tests
- GitHub Actions continuous integration

## Documentation

- Professional project README
- Software architecture
- Threat model
- API reference
- Deployment guide
- Security policy
- Contribution guide
- Project roadmap

## Verified Behaviors

- Maven test suite passes
- User registration succeeds with a compliant password
- Duplicate registration is rejected
- Valid credentials produce a JWT
- JWT authorizes access to `/api/users/me`
- A normal user is denied access to `/api/admin/status`
- Protected access without authentication is denied
- `/actuator/health` reports `UP`
- Passwords are persisted as BCrypt hashes
- User roles and security audit events are persisted

## Known Limitations

This is an educational reference implementation, not a production-certified IAM platform. Version 1.0.0 does not include:

- Multi-Factor Authentication
- Refresh-token rotation
- Token revocation
- Password reset
- Email verification
- Account lockout
- Rate limiting
- External identity providers
- OAuth2 or OpenID Connect
- Centralized SIEM integration
- Production TLS termination

## Planned Next Release

Version 1.1.0 is planned to introduce TOTP-based Multi-Factor Authentication, enrollment, verification, and recovery-code concepts.

## Author

Abhishek Gaddam

## License

MIT License
