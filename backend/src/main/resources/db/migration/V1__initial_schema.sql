CREATE TABLE app_users (
    id UUID PRIMARY KEY,
    email VARCHAR(254) NOT NULL UNIQUE,
    password_hash VARCHAR(100) NOT NULL,
    created_at TIMESTAMPTZ NOT NULL,
    updated_at TIMESTAMPTZ NOT NULL
);

CREATE TABLE players (
    id UUID PRIMARY KEY,
    user_id UUID NOT NULL REFERENCES app_users(id),
    player_tag VARCHAR(16) NOT NULL UNIQUE,
    player_name VARCHAR(128) NOT NULL,
    town_hall_level INTEGER NOT NULL,
    last_successful_refresh_at TIMESTAMPTZ NOT NULL,
    created_at TIMESTAMPTZ NOT NULL,
    updated_at TIMESTAMPTZ NOT NULL
);
CREATE INDEX idx_players_user_id ON players(user_id);

CREATE TABLE player_snapshots (
    id UUID PRIMARY KEY,
    player_id UUID NOT NULL REFERENCES players(id),
    captured_at TIMESTAMPTZ NOT NULL,
    experience_level INTEGER NOT NULL,
    trophies INTEGER NOT NULL,
    heroes_json JSONB NOT NULL,
    created_at TIMESTAMPTZ NOT NULL
);
CREATE INDEX idx_player_snapshots_player_captured ON player_snapshots(player_id, captured_at DESC);

CREATE TABLE recommendations (
    id UUID PRIMARY KEY,
    snapshot_id UUID NOT NULL REFERENCES player_snapshots(id),
    recommendation_type VARCHAR(64) NOT NULL,
    priority VARCHAR(16) NOT NULL,
    reason_codes_json JSONB NOT NULL,
    explanation TEXT NOT NULL,
    rule_version VARCHAR(64) NOT NULL,
    created_at TIMESTAMPTZ NOT NULL
);
CREATE INDEX idx_recommendations_snapshot_id ON recommendations(snapshot_id);
