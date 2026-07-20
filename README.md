# Clash Coach AI

An explainable Clash of Clans progression coach. Sprint 1 lets an authenticated player link a Clash account, refresh a saved profile through the official Clash API, and receive a deterministic hero-upgrade recommendation.

## Architecture

- `backend`: Java 8, Spring Boot 2.7, external Tomcat 9, PostgreSQL, Redis, Flyway.
- `frontend`: Next.js 15, React, TypeScript, Tailwind CSS.

See `docs/` for the product, architecture, database, API, recommendation-engine, UI, and roadmap specifications.

## Local development

1. Provision PostgreSQL and Redis outside this repository, then create the `clash_coach` database.
2. Configure `DATABASE_URL`, `DATABASE_USERNAME`, `DATABASE_PASSWORD`, `JWT_SECRET`, and `CLASH_API_TOKEN` as Tomcat environment variables or JVM system properties. Obtain `CLASH_API_TOKEN` only from the official Clash of Clans Developer API portal.
3. With JDK 8 and Maven installed, run `mvn clean package` from `backend`, then deploy `target/clash-coach-api-0.1.0-SNAPSHOT.war` to Tomcat 9's `webapps/` directory.
4. Set `NEXT_PUBLIC_API_URL` to the deployed API URL, run `npm install && npm run build` in `frontend`, and serve the Next.js application.

With the default WAR name, the backend is available at `http://localhost:8080/clash-coach-api/api/v1`; its health endpoint is `/api/v1/actuator/health` under that context path.
