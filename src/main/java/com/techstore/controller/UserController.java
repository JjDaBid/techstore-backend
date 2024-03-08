package com.techstore.controller;

import com.techstore.auth.LoginResponse;
import com.techstore.model.User;
import com.techstore.services.AuthService;
import com.techstore.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:5173/admin")
//@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private AuthService authService;

    @GetMapping("/users")
    public List<User> listUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("users/{id}")
    public ResponseEntity<User> listUserById(@PathVariable Long id){
        User foundUser = userService.getUserById(id);
        if (foundUser != null) {
            return ResponseEntity.ok(foundUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userService.createUser(user);
        if (createdUser != null) {
            return ResponseEntity.ok(createdUser);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> userProduct(@PathVariable Long id, @RequestBody User userRequest){
        User user = userService.getUserById(id);
        user.setUsername(userRequest.getUsername());
        user.setPassword(userRequest.getPassword());
        User userUpdated = userService.updateUser(id, user);
        return ResponseEntity.ok(userUpdated);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/api/v1/users")
    public ResponseEntity<?> login(@RequestBody User user) {
        // Verifica las credenciales y genera un token de autenticación si son válidas
        String token = authService.authenticate(user.getUsername(), user.getPassword());
        if (token != null) {
            return ResponseEntity.ok(new LoginResponse(token));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

}
