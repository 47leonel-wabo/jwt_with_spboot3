package com.wbt.jwt_secure_app_spbt3.auth;

public record AuthRequest(
        String email,
        String password) {
}
