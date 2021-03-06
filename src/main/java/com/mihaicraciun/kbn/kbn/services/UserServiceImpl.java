package com.mihaicraciun.kbn.kbn.services;

import com.mihaicraciun.kbn.kbn.model.User;
import com.mihaicraciun.kbn.kbn.model.User.Role;
import com.mihaicraciun.kbn.kbn.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private String GUEST_EMAIL = "guest";

    private String GUEST_NAME = "guest";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User create(String fullName, String email, String password) {
        User user = new User();
        user.setFullName(fullName);
        user.setEmail(email);
        user.setPassword(password);
        return user;
    }

    @Override
    public User update(User user, String fullName, String email, String password) {
        user.setFullName(fullName);
        user.setEmail(email);
        user.setPassword(password);
        return user;
    }

    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public User findByEmail(String username) {
        return userRepository.findByEmail(username);
    }

    @Override
    public User guestUser() {
        User guest = userRepository.findByEmail(GUEST_EMAIL);
        if (guest == null) {
            guest = new User();
            guest.setEmail(GUEST_EMAIL);
            guest.setFullName(GUEST_NAME);
            guest.setPassword(GUEST_NAME);
            guest.setRole(Role.USER);
            userRepository.save(guest);
        }
        return guest;
    }

    @Override
    public User currentUser() {
        if (SecurityContextHolder.getContext().getAuthentication() != null &&
                SecurityContextHolder.getContext().getAuthentication().isAuthenticated() &&
                //when Anonymous Authentication is enabled
                !(SecurityContextHolder.getContext().getAuthentication()
                        instanceof AnonymousAuthenticationToken)) {
            String userName = SecurityContextHolder.getContext().getAuthentication().getName();
            return findByEmail(userName);
        }
        return guestUser();
    }

    
}