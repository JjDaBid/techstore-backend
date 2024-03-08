package com.techstore.services;

import com.techstore.exception.ResourceNotFoundException;
import com.techstore.model.User;
import com.techstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(Long id, User user) {
        User foundUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        foundUser.setUsername(user.getUsername());
        foundUser.setPassword(user.getPassword());

        User userUpdated = userRepository.save(foundUser);
        return userUpdated;
    }

    @Override
    public ResponseEntity<Map<String, Boolean>> deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        userRepository.delete(user);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
