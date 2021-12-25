package com.panicatthedevops.campuscarebackend.service;

import com.panicatthedevops.campuscarebackend.repository.InstructorRepository;
import com.panicatthedevops.campuscarebackend.repository.StaffRepository;
import com.panicatthedevops.campuscarebackend.repository.StudentRepository;
import com.panicatthedevops.campuscarebackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository, StudentRepository studentRepository, StaffRepository staffRepository, InstructorRepository instructorRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findById(Long.parseLong(username)).orElse(null);
    }
}
