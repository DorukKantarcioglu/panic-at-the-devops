package com.panicatthedevops.pandemicmanager.repository;

import com.panicatthedevops.pandemicmanager.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findByHesCode(String hesCode);
}
