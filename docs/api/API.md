# API Contract

All application endpoints are under `/api/v1` relative to the Tomcat WAR context and return JSON. Errors have `{ "code", "message", "fieldErrors"? }`.

| Method | Path | Purpose |
| --- | --- | --- |
| POST | `/auth/register` | Create account; returns bearer token. |
| POST | `/auth/login` | Authenticate; returns bearer token. |
| GET | `/players/me` | Return the caller's linked player and latest snapshot. |
| PUT | `/players/me` | Link the caller's player tag. |
| POST | `/players/me/refresh` | Fetch and persist a fresh player snapshot. |
| GET | `/players/me/recommendation` | Return latest deterministic recommendation. |

`PUT /players/me` accepts `{ "playerTag": "#ABC123" }`. Tags are canonicalized to uppercase and must contain `#` followed by `[0289PYLQGRJCUV]{3,15}`. Authentication failures return `401`; data owned by another user returns `409`; unavailable provider data returns `502`.

For local development, CORS permits the Next.js origin `http://localhost:3000` only.
