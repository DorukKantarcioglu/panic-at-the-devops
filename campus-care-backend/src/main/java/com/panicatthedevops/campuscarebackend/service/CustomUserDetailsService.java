package com.panicatthedevops.campuscarebackend.service;

import com.panicatthedevops.campuscarebackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Service layer for Spring authentication procedure
 * @version 1.0
 * @see org.springframework.security.core.userdetails.UserDetailsService
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     *
     * @param username Username to be authenticated to the system
     * @return UserDetails of the user with the given username
     * @throws UsernameNotFoundException When the username does not match the database values
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findById(Long.parseLong(username)).orElse(null);
    }
}
