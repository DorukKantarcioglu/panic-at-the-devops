package com.panicatthedevops.campuscarebackend.service;

import com.panicatthedevops.campuscarebackend.entity.Instructor;
import com.panicatthedevops.campuscarebackend.entity.Staff;
import com.panicatthedevops.campuscarebackend.entity.User;
import com.panicatthedevops.campuscarebackend.entity.Student;
import com.panicatthedevops.campuscarebackend.exception.UserNotFoundException;
import com.panicatthedevops.campuscarebackend.repository.InstructorRepository;
import com.panicatthedevops.campuscarebackend.repository.StaffRepository;
import com.panicatthedevops.campuscarebackend.repository.StudentRepository;
import com.panicatthedevops.campuscarebackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

import java.util.ArrayList;
import java.util.Arrays;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    private final StudentRepository studentRepository;
    private final StaffRepository staffRepository;
    private final InstructorRepository instructorRepository;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository, StudentRepository studentRepository, StaffRepository staffRepository, InstructorRepository instructorRepository) {
        this.userRepository = userRepository;
        this.studentRepository = studentRepository;
        this.staffRepository = staffRepository;
        this.instructorRepository = instructorRepository;
    }

    public User build(Long id){
        if(studentRepository.existsById(id)){
            Student student = studentRepository.findById(id).get();
            List<SimpleGrantedAuthority> list = new ArrayList<>();
            list.add(new SimpleGrantedAuthority("ROLE_STUDENT"));
            student.setAuthorities(list);
            return student;
        }else if(instructorRepository.existsById(id)){
            Instructor instructor = instructorRepository.findById(id).get();
            List<SimpleGrantedAuthority> list = new ArrayList<>();
            list.add(new SimpleGrantedAuthority("ROLE_INSTRUCTOR"));
            instructor.setAuthorities(list);
            return instructor;
        }else if(staffRepository.existsById(id)){
            Staff staff = staffRepository.findById(id).get();
            List<SimpleGrantedAuthority> list = new ArrayList<>();
            list.add(new SimpleGrantedAuthority("ROLE_STAFF"));
            staff.setAuthorities(list);
            return staff;
        }
        throw new UserNotFoundException("User with id " + id + " does not exist");
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findById(Long.parseLong(username)).orElse(null);
    }
}
