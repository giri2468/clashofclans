package com.clashcoach.security;

import static org.junit.jupiter.api.Assertions.*;
import com.clashcoach.user.User;
import org.junit.jupiter.api.Test;

class TokenServiceTest {
    private final TokenService tokens = new TokenService("01234567890123456789012345678901", java.time.Duration.ofMinutes(5));
    @Test void issuesAndParsesATokenForTheSameUser() { User user = new User("coach@example.com", "hash"); assertEquals(user.getId(), tokens.parse(tokens.issue(user))); }
    @Test void rejectsTamperedTokens() { assertThrows(TokenService.InvalidTokenException.class, () -> tokens.parse("invalid.token")); }
}
