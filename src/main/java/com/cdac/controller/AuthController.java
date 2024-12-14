
package com.cdac.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cdac.config.JwtUtil;
import com.cdac.model.JwtRequest;
import com.cdac.model.JwtResponse;

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin(origins = "https://web-xi-orcin.vercel.app", allowCredentials = "true")
@Slf4j
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) {
        log.info("Authentication request received for user: {}", authenticationRequest.getUsername());
        
        try {
            // Authenticate the user
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getUsername(),
                    authenticationRequest.getPassword()
                )
            );

            // Get user details
            final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

            // Generate JWT token
            final String token = jwtTokenUtil.generateToken(userDetails.getUsername());

            // Log successful authentication
            log.info("Authentication successful for user: {}", authenticationRequest.getUsername());

            // Return the token
            return ResponseEntity.ok(new JwtResponse(token));

        } catch (BadCredentialsException e) {
            log.error("Invalid credentials for user: {}", authenticationRequest.getUsername());
            return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(new ErrorResponse("Invalid username or password"));
        } catch (Exception e) {
            log.error("Authentication error for user: {}", authenticationRequest.getUsername(), e);
            return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse("An error occurred during authentication"));
        }
    }
}

// Add this class at the bottom of the file or in a separate file
class ErrorResponse {
    private String message;

    public ErrorResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
