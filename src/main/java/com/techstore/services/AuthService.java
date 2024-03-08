package com.techstore.services;

import com.techstore.model.User;
import com.techstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String authenticate(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            return username + ":" + password; // Este es solo un ejemplo, en una aplicación real deberías generar un token de autenticación seguro
        } else {
            return null;
        }
    }
}
