package chinthana.photographyweb.controller;

import chinthana.photographyweb.entity.User;
import chinthana.photographyweb.security.JwtTokenProvider;
import chinthana.photographyweb.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/v1/user")
@AllArgsConstructor
public class UserController {
    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;
    //Save users
    @PostMapping("/save")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User savedUser = userService.saveUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody User loginUser) {
        User authenticatedUser = userService.loginUser(loginUser.getEmail(), loginUser.getPassword());

        if (authenticatedUser != null) {
            String token = jwtTokenProvider.generateToken(authenticatedUser.getEmail());
            return ResponseEntity.ok(token);
        } else {
            return ResponseEntity.badRequest().body("Invalid email or password");
        }
    }

    @PostMapping("/validateToken")
    public boolean validateToken(@RequestBody Map<String, String> request) {
        String token = request.get("token");
        return jwtTokenProvider.validateToken(token);
    }
}

