package com.wbt.jwt_secure_app_spbt3.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = {"/api/n1/auth"})
public class AuthenticationController {

    private final AuthenticationService mAuthenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        mAuthenticationService = authenticationService;
    }

    @PostMapping(path = {"/register"})
    public ResponseEntity<AuthResponse> register(final @RequestBody RegistrationRequest request) {
        return ResponseEntity.ok(mAuthenticationService.register(request));
    }

    @PostMapping(path = {"/authenticate"})
    public ResponseEntity<AuthResponse> authenticate(final @RequestBody AuthRequest request) {
        return ResponseEntity.ok(mAuthenticationService.authenticate(request));
    }

}
