# Sprint 1 Product Requirements

## Goal

Deliver the first safe, explainable coaching loop: an authenticated player links a Clash of Clans account, refreshes its profile from the official API, and receives a deterministic upgrade priority.

## In scope

- Email/password account creation and sign-in.
- One Clash player per account in the first release.
- Player-tag validation, link, persisted snapshots, and manual refresh.
- A profile view that shows the latest successful snapshot.
- One deterministic recommendation: prioritize the lowest-level available hero before non-hero progression. The response contains a rule version and reason codes.

## Out of scope

- Gameplay automation, credential collection, clan management, payments, social features, scheduled refreshes, and LLM-generated decisions.
- Full building, troop, pet, and equipment optimisation; these require versioned game-data catalogues and are deferred.

## Success criteria

1. A signed-in user can link a valid player tag and see its profile.
2. Refresh failures are intelligible and do not overwrite the last successful snapshot.
3. The same snapshot always produces the same recommendation.
4. A user cannot read or modify another user's player.

## Open product decisions

- Production identity provider and email-verification requirements.
- Refresh quota, cache duration, and account-linking policy for a tag previously linked by another user.
