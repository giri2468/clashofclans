package com.clashcoach.security;
import com.clashcoach.user.*; import java.util.Locale; import org.springframework.security.crypto.password.PasswordEncoder; import org.springframework.stereotype.Service; import org.springframework.transaction.annotation.Transactional;
@Service public class AuthService {
    private final UserRepository users; private final PasswordEncoder passwords; private final TokenService tokens;
    public AuthService(UserRepository users, PasswordEncoder passwords, TokenService tokens) { this.users = users; this.passwords = passwords; this.tokens = tokens; }
    @Transactional public AuthDtos.AuthResponse register(AuthDtos.Credentials credentials) { String email = credentials.getEmail().trim().toLowerCase(Locale.ROOT); if (users.findByEmail(email).isPresent()) throw new DuplicateEmailException(); User user = users.save(new User(email, passwords.encode(credentials.getPassword()))); return response(user); }
    public AuthDtos.AuthResponse login(AuthDtos.Credentials credentials) { User user = users.findByEmail(credentials.getEmail().trim().toLowerCase(Locale.ROOT)).filter(candidate -> passwords.matches(credentials.getPassword(), candidate.getPasswordHash())).orElseThrow(InvalidCredentialsException::new); return response(user); }
    private AuthDtos.AuthResponse response(User user) { return new AuthDtos.AuthResponse(tokens.issue(user), "Bearer", user.getEmail()); }
    public static class DuplicateEmailException extends RuntimeException { } public static class InvalidCredentialsException extends RuntimeException { }
}
