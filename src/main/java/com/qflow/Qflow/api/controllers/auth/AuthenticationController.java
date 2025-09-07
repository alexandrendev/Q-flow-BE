package com.qflow.Qflow.api.controllers.auth;

import com.qflow.Qflow.api.requests.auth.AuthenticationDTO;
import com.qflow.Qflow.api.requests.auth.RegisterDTO;
import com.qflow.Qflow.core.entity.user.User;
import com.qflow.Qflow.core.ports.UserRepository;
import com.qflow.Qflow.infra.security.jwt.JwtTokenService;
import com.qflow.Qflow.infra.security.MyUserDetails;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private JwtTokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data) {
        try {
            var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());

            var authentication = authenticationManager.authenticate(usernamePassword);

            var userDetails = (MyUserDetails) authentication.getPrincipal();
            var token = tokenService.generateToken(userDetails.getUser());

            System.out.println("Token gerado com sucesso!");
            return ResponseEntity.ok(token);
        } catch (Exception e) {
            System.out.println("ERRO na autenticação: " + e.getClass().getSimpleName() + " - " + e.getMessage());
            return ResponseEntity.status(401).body("Authentication failed: " + e.getMessage());
        }
    }
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data) {
        if (this.userRepository.findByEmail(data.email()) != null) return ResponseEntity.badRequest().body("User already exists");

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.tenantId(), data.name(), data.email(), encryptedPassword, data.role());

        this.userRepository.save(newUser);

        return ResponseEntity.ok().build();
    }

    @Autowired
    public AuthenticationController(AuthenticationManager authenticationManager, UserRepository userRepository, JwtTokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.tokenService = tokenService;
    }
}
