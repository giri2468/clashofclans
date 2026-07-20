# System Architecture

Clash Coach AI is a web application with a Next.js 15 client and a Java 8/Spring Boot 2.7 API packaged as a WAR for external Tomcat 9. PostgreSQL stores durable domain data; Redis is reserved for short-lived caching and rate limiting.

## Boundaries

- `web`: routes, accessible UI, typed HTTP client; it contains no coaching rules.
- `interfaces`: REST controllers, request validation, exception mapping, and security adapters.
- `application`: use cases and transaction boundaries.
- `domain`: entities, value objects, recommendation rules, and repository ports.
- `infrastructure`: JPA repositories, Flyway migrations, JWT implementation, and Clash API HTTP client.

Dependencies point inward. Controllers do not access repositories directly. The Clash API client is an infrastructure adapter; provider DTOs never leave it.

## Refresh flow

`authenticated request -> PlayerController -> RefreshPlayerUseCase -> ClashApiClient -> snapshot mapper -> PlayerSnapshotRepository -> RecommendationService -> response`.

The last successful snapshot remains visible after a refresh failure. Recommendations consume a persisted snapshot, include their rule version, and are never selected by an LLM.

## Deployment

The backend is built with `mvn clean package` on JDK 8 and deployed as `clash-coach-api-0.1.0-SNAPSHOT.war` to Tomcat 9. Database, Redis, and configuration are externally provisioned. Docker is not used.
