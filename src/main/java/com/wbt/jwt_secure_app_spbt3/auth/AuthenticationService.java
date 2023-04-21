package com.wbt.jwt_secure_app_spbt3.auth;

import com.wbt.jwt_secure_app_spbt3.config.JwtService;
import com.wbt.jwt_secure_app_spbt3.user.User;
import com.wbt.jwt_secure_app_spbt3.user.UserRepository;
import com.wbt.jwt_secure_app_spbt3.user.UserRole;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final UserRepository mUserRepository;
    private final PasswordEncoder mPasswordEncoder;
    private final JwtService mJwtService;
    private final AuthenticationManager mAuthenticationManager;

    public AuthenticationService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService,
            AuthenticationManager authenticationManager) {
        mUserRepository = userRepository;
        mPasswordEncoder = passwordEncoder;
        mJwtService = jwtService;
        mAuthenticationManager = authenticationManager;
    }

    public AuthResponse register(final RegistrationRequest request) {
        var user = User.builder()
                .name(request.name())
                .age(request.age())
                .email(request.email())
                .password(mPasswordEncoder.encode(request.password()))
                .role(UserRole.USER)
                .build();
        mUserRepository.save(user);
        var jwtToken = mJwtService.generateToken(user);
        return new AuthResponse(jwtToken);
    }

    public AuthResponse authenticate(final AuthRequest request) {
        mAuthenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(request.email(), request.password())); // handle authentication
        // if authentication is fine, then proceed
        var user = mUserRepository
                .findByEmail(request.email())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new AuthResponse(mJwtService.generateToken(user));
    }
}
