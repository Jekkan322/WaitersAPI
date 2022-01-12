package com.server.demo.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.server.demo.config.jwt.JwtUtils;
import com.server.demo.entities.ERole;
import com.server.demo.entities.Role;
import com.server.demo.entities.User;
import com.server.demo.pojo.JwtResponse;
import com.server.demo.pojo.LoginRequest;
import com.server.demo.pojo.MessageResponse;
import com.server.demo.pojo.SignupRequest;
import com.server.demo.repositories.RoleRepository;
import com.server.demo.repositories.UserRepository;
import com.server.demo.service.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRespository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtUtils jwtUtils;



    @PostMapping("/signin")
    public ResponseEntity<?> authUser(@RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignupRequest signupRequest) {
        try{
            if (userRespository.existsByUsername(signupRequest.getUsername())) {
                return ResponseEntity
                        .badRequest()
                        .body(new MessageResponse("Error: Username is exist"));
            }


            User user = new User(signupRequest.getUsername(),
                    passwordEncoder.encode(signupRequest.getPassword()));

            Set<String> reqRoles = signupRequest.getRoles();
            Set<Role> roles = new HashSet<>();

            if (reqRoles == null) {
                Role userRole = roleRepository
                        .findByName(ERole.ROLE_WAITER)
                        .orElseThrow(() -> new RuntimeException("Error, Role waiter is not found"));
                roles.add(userRole);
            } else {
                reqRoles.forEach(r -> {
                    switch (r) {
                        case "admin":
                            Role adminRole = roleRepository
                                    .findByName(ERole.ROLE_ADMIN)
                                    .orElseThrow(() -> new RuntimeException("Error, Role ADMIN is not found"));
                            roles.add(adminRole);

                            break;
                        default:
                            Role userRole = roleRepository
                                    .findByName(ERole.ROLE_WAITER)
                                    .orElseThrow(() -> new RuntimeException("Error, Role USER is not found"));
                            roles.add(userRole);
                    }
                });
            }
            user.setRoles(roles);
            userRespository.save(user);
            return ResponseEntity.ok(new MessageResponse("User CREATED"));
        }catch (Exception e){
                return ResponseEntity.badRequest().body("Произошла ошибка"+e.getMessage());
        }
    }
}
