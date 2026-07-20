package com.clashcoach.security;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/** HTTP DTOs for authentication endpoints. */
public final class AuthDtos {
    private AuthDtos() { }
    public static class Credentials {
        @NotBlank @Email @Size(max = 254) private String email;
        @NotBlank @Size(min = 12, max = 128) private String password;
        public String getEmail() { return email; } public void setEmail(String email) { this.email = email; }
        public String getPassword() { return password; } public void setPassword(String password) { this.password = password; }
    }
    public static class AuthResponse {
        private final String accessToken; private final String tokenType; private final String email;
        public AuthResponse(String accessToken, String tokenType, String email) { this.accessToken = accessToken; this.tokenType = tokenType; this.email = email; }
        public String getAccessToken() { return accessToken; } public String getTokenType() { return tokenType; } public String getEmail() { return email; }
    }
}
