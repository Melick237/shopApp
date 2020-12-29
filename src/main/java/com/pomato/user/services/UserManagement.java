package com.pomato.user.services;

import com.pomato.token.TokenServiceImpl;
import com.pomato.user.entities.User;
import com.pomato.user.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserManagement {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    public PasswordEncoder passwordEncoder;

    @Autowired
    public TokenServiceImpl tokenService;

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public Optional<User> findUserById(Long id) {
        Optional<User> byId = userRepository.findById(id);
        return byId;
    }

    public void saveUser(final User user){
        if(user == null) throw new NullPointerException("User must not be null");
        userRepository.save(user);
    }

    public void deleteUser(User user){
        if (user == null) throw new NullPointerException("User must not be null.");
        userRepository.deleteById(user.getId());
    }

    public Optional<User> findUserByEmail(String email) {
        if (email == null) throw new NullPointerException("EMail must not be null.");
        if (email.isEmpty()) throw new NullPointerException("EMail must not be empty.");

        return userRepository.findFirstByEmail(email.toLowerCase().trim());
    }

    public void generateAndSaveNewValidationTokenForUser(final User user) {
        if (user == null) throw new NullPointerException("User must not be null.");
        String token = tokenService.createToken(user.getEmail());
        user.setValidationToken(token);
        saveUser(user);
    }


    public void updatePassword(final String newPassword, final User user) {
        if (newPassword == null) throw new NullPointerException("NewPassword must not be null.");
        if (newPassword.isEmpty()) throw new IllegalArgumentException("NewPassword must not be empty.");
        if (user == null) throw new NullPointerException("User must not be null.");

        user.hashedPassword = passwordEncoder.encode(newPassword);
        saveUser(user);
    }

    public void rehashPassword(final String password, final User user) {
        this.updatePassword(password, user);
    }
}