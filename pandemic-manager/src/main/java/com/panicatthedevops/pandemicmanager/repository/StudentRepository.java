package com.panicatthedevops.pandemicmanager.repository;

import com.panicatthedevops.pandemicmanager.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByHesCode(String hesCode);
    boolean existsByHesCode(String hesCode);
}
