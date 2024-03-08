package com.techstore.services;

import com.techstore.model.User;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.Map;

public interface UserService {

    List<User> getAllUsers();
    User getUserById(Long id);
    User createUser(User user);
    User updateUser(Long id, User user);
    ResponseEntity<Map<String, Boolean>> deleteUser(Long id);
}
