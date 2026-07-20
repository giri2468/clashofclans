package com.clashcoach.security;

import com.clashcoach.user.User;
import java.nio.charset.StandardCharsets; import java.time.Duration; import java.time.Instant; import java.util.*; import javax.crypto.Mac; import javax.crypto.spec.SecretKeySpec;
import org.springframework.beans.factory.annotation.Value; import org.springframework.stereotype.Service;

@Service
public class TokenService {
    private final byte[] secret; private final Duration ttl;
    public TokenService(@Value("${app.security.jwt-secret}") String secret, @Value("${app.security.token-ttl}") Duration ttl) { if (secret.length() < 32) throw new IllegalArgumentException("JWT secret must be at least 32 bytes"); this.secret = secret.getBytes(StandardCharsets.UTF_8); this.ttl = ttl; }
    public String issue(User user) { Instant expiry = Instant.now().plus(ttl); String body = user.getId() + "." + expiry.getEpochSecond(); return Base64.getUrlEncoder().withoutPadding().encodeToString(body.getBytes(StandardCharsets.UTF_8)) + "." + signature(body); }
    public UUID parse(String token) { try { String[] parts = token.split("\\."); if (parts.length != 2) throw new IllegalArgumentException(); String body = new String(Base64.getUrlDecoder().decode(parts[0]), StandardCharsets.UTF_8); if (!constantTimeEquals(signature(body), parts[1])) throw new IllegalArgumentException(); String[] fields = body.split("\\."); if (fields.length != 2 || Instant.now().isAfter(Instant.ofEpochSecond(Long.parseLong(fields[1])))) throw new IllegalArgumentException(); return UUID.fromString(fields[0]); } catch (Exception exception) { throw new InvalidTokenException(); } }
    private String signature(String body) { try { Mac mac = Mac.getInstance("HmacSHA256"); mac.init(new SecretKeySpec(secret, "HmacSHA256")); return Base64.getUrlEncoder().withoutPadding().encodeToString(mac.doFinal(body.getBytes(StandardCharsets.UTF_8))); } catch (Exception exception) { throw new IllegalStateException("Unable to sign token", exception); } }
    private boolean constantTimeEquals(String left, String right) { return java.security.MessageDigest.isEqual(left.getBytes(StandardCharsets.UTF_8), right.getBytes(StandardCharsets.UTF_8)); }
    public static class InvalidTokenException extends RuntimeException { }
}
