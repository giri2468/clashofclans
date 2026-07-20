# Database Design

PostgreSQL is the system of record. Flyway owns schema evolution. All timestamps are `timestamptz` in UTC.

## Tables

- `app_users`: account identity, unique lowercase email, BCrypt password hash, timestamps.
- `players`: a user-owned normalized Clash tag, display name, Town Hall level, last successful refresh, timestamps. A tag can have one owner.
- `player_snapshots`: immutable provider payload projection for a player, captured timestamp, level, experience, trophies, heroes JSON, and API response metadata.
- `recommendations`: immutable rule outcome tied to a snapshot, recommendation type, priority, reason codes JSON, explanation, and rule version.

The unique constraints are `app_users.email` and `players.player_tag`. Foreign keys are indexed. Snapshots and recommendations are append-only; cleanup/retention is a future operational policy.
