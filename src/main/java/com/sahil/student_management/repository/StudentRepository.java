package com.sahil.student_management.repository;

import com.sahil.student_management.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {

    boolean existsByEmail(String email);

    Page<Student> findByCourseIgnoreCase(String course, Pageable pageable);
}