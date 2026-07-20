# Recommendation Engine

The recommendation engine is deterministic business logic. An LLM may later rewrite a completed rule outcome into friendlier prose, but it must never choose the recommendation.

## Sprint 1 rule: `hero-lowest-level-v1`

For a snapshot with at least one hero, choose the hero with the lowest level. Ties sort by hero name. Return priority `HIGH`, reason code `LOWEST_LEVEL_HERO`, and an explanation that names the selected hero and its level. If hero data is absent, return `INSUFFICIENT_DATA` with priority `INFO`.

Every outcome persists the input snapshot ID and a semantic rule version. Rule fixtures are regression-tested.
